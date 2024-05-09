import { Component, inject } from '@angular/core';
import { AuthService, UserService } from '../services';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss',
})
export class HeaderComponent {
  readonly userServices = inject(UserService);
  readonly authService = inject(AuthService);

  logout() {
    this.authService.signOut();
  }
}
