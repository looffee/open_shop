import { Injectable, computed, effect, inject } from '@angular/core';
import { JwtTokenStorageService } from './jwt-token-storage.service';

@Injectable()
export class UserService {
  readonly jwtStorageService = inject(JwtTokenStorageService);

  readonly isAuthenticated = computed(() => this.jwtStorageService.hasToken());
}
