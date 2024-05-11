import { Component, afterNextRender } from '@angular/core';
import { SharedModule } from './shared.module';
import { MaterialModule } from './material.module';
import {
  AuthService,
  CategoryService,
  JwtTokenStorageService,
  UserService,
} from './services';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [SharedModule, MaterialModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
  providers: [UserService, AuthService, CategoryService],
})
export class AppComponent {
  constructor(jstStorageService: JwtTokenStorageService) {
    afterNextRender(() => {
      jstStorageService.checkToken();
    });
  }
}
