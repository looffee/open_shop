import { Injectable, computed, effect, inject } from '@angular/core';
import { JwtTokenStorageService } from './jwt-token-storage.service';

import * as jose from 'jose';

export enum Role {
  Admin = 'ADMIN',
  User = 'USER',
}

export interface User {
  id: number;
  email: string;
  firstName: string;
  lastName: string;
  phone: string;
}

interface JwtClaims {
  user: User;
  scope: string;
}

@Injectable()
export class UserService {
  readonly jwtStorageService = inject(JwtTokenStorageService);

  readonly isAuthenticated = computed(() => this.jwtStorageService.hasToken());
  readonly jwtClaims = computed(() => {
    const token = this.jwtStorageService.token();

    if (token === null) {
      return null;
    }

    const payload = jose.decodeJwt<JwtClaims>(token);

    return payload;
  });
  readonly user = computed(() => this.jwtClaims()?.user);
  readonly roles = computed(() => this.jwtClaims()?.scope.split(' '));
  readonly isAdmin = computed(
    () => this.roles()?.includes(Role.Admin) ?? false
  );
  readonly isUser = computed(() => this.roles()?.includes(Role.User) ?? false);
}
