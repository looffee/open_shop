import { Routes } from '@angular/router';
import { RootPageComponent } from './root-page/root-page.component';
import { SignInPageComponent } from './sign-in-page/sign-in-page.component';
import { AdminPageComponent } from './admin/admin-page/admin-page.component';
import { CategoriesPageComponent } from './admin/categories-page/categories-page.component';

export const routes: Routes = [
  {
    path: '',
    component: RootPageComponent,
  },
  {
    path: 'sign-in',
    component: SignInPageComponent,
  },
  {
    path: 'admin',
    component: AdminPageComponent,
    children: [
      {
        path: 'categories',
        component: CategoriesPageComponent,
      },
    ],
  },
];
