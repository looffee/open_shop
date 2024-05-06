import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  readonly http = inject(HttpClient);

  signIn(phoneNumber: string, password: string): Observable<string> {
    return this.http.post<string>('/auth/login', { phoneNumber, password });
  }
}
