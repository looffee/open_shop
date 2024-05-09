import { Injectable, Signal, signal } from '@angular/core';

@Injectable()
export class JwtTokenStorageService {
  private TOKEN_KEY = 'jwt-token';

  private readonly _hasToken = signal<boolean>(false);
  readonly hasToken: Signal<boolean> = this._hasToken;

  constructor() {}

  getToken(): string | null {
    return localStorage.getItem(this.TOKEN_KEY);
  }

  saveToken(token: string): void {
    localStorage.setItem(this.TOKEN_KEY, token);
    this._hasToken.set(true);
  }

  removeToken(): void {
    localStorage.removeItem(this.TOKEN_KEY);
    this._hasToken.set(false);
  }

  checkToken(): void {
    this._hasToken.set(this.getToken() !== null);
  }
}
