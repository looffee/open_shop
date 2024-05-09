import { Component, afterNextRender } from '@angular/core';
import { SharedModule } from './shared.module';
import { MaterialModule } from './material.module';
import { AuthService, JwtTokenStorageService, UserService } from './services';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { JwtAuthHeaderWriterInterceptor } from './interceptors';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [SharedModule, MaterialModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
  providers: [
    JwtTokenStorageService,
    UserService,
    AuthService,
    {
      provide: HTTP_INTERCEPTORS,
      useExisting: JwtAuthHeaderWriterInterceptor,
      multi: true,
    },
  ],
})
export class AppComponent {
  constructor(jstStorageService: JwtTokenStorageService) {
    afterNextRender(() => {
      jstStorageService.checkToken();
    });
  }
}
