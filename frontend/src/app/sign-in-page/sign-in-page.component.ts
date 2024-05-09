import { Component, inject } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { SharedModule } from '../shared.module';
import { MaterialModule } from '../material.module';
import { AuthService } from '../services';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sign-in-page',
  standalone: true,
  imports: [SharedModule, MaterialModule],
  templateUrl: './sign-in-page.component.html',
  styleUrl: './sign-in-page.component.scss',
})
export class SignInPageComponent {
  readonly authService = inject(AuthService);
  readonly fb = inject(FormBuilder);
  readonly form = this.fb.group({
    phoneNumber: this.fb.control('', [
      Validators.required,
      Validators.pattern(/^\d{6,}$/),
    ]),
    password: this.fb.control('', [
      Validators.required,
      Validators.minLength(8),
    ]),
  });
  readonly router = inject(Router);

  onSubmit() {
    if (this.form.invalid) throw new Error('Invalid form');

    const { phoneNumber, password } = this.form.value;

    if (phoneNumber === undefined || phoneNumber === null)
      throw new Error('Phone number is required');
    if (password === undefined || password === null)
      throw new Error('Password is required');

    this.authService.signIn(phoneNumber, password).subscribe({
      next: () => {
        this.router.navigate(['/']);
      },
      error: (error) => {
        console.error('Failed to sign in', error);
      },
    });
  }
}
