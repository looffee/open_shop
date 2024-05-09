import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable, map, tap } from 'rxjs';
import { JwtTokenStorageService } from './jwt-token-storage.service';

@Injectable()
export class AuthService {
  readonly http = inject(HttpClient);
  readonly jwtStorageService = inject(JwtTokenStorageService);

  signIn(phoneNumber: string, password: string): Observable<void> {
    return this.http
      .post('/auth/login', { phoneNumber, password }, { responseType: 'text' })
      .pipe(
        tap((token) => this.jwtStorageService.saveToken(token)),
        map(() => undefined)
      );
  }

  signOut(): void {
    this.jwtStorageService.removeToken();
  }
}
