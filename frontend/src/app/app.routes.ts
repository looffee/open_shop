import { Routes } from '@angular/router';
import { RootPageComponent } from './root-page/root-page.component';
import { SignInPageComponent } from './sign-in-page/sign-in-page.component';

export const routes: Routes = [
  {
    path: '',
    component: RootPageComponent,
  },
  {
    path: 'sign-in',
    component: SignInPageComponent,
  },
];
