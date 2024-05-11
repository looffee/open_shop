import { Injectable, PLATFORM_ID, Signal, inject, signal } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';

@Injectable()
export class JwtTokenStorageService {
  private TOKEN_KEY = 'jwt-token';
  readonly platformId = inject(PLATFORM_ID);

  private readonly _hasToken = signal<boolean>(false);
  readonly hasToken: Signal<boolean> = this._hasToken;

  constructor() {}

  get storage(): Storage {
    if (isPlatformBrowser(this.platformId)) {
      return window.localStorage;
    }

    throw new Error('localStorage is available only in browser platform!');
  }

  getToken(): string | null {
    try {
      return this.storage.getItem(this.TOKEN_KEY);
    } catch (e) {
      console.error(e);
    }

    return null;
  }

  saveToken(token: string): void {
    this.storage.setItem(this.TOKEN_KEY, token);
    this._hasToken.set(true);
  }

  removeToken(): void {
    this.storage.removeItem(this.TOKEN_KEY);
    this._hasToken.set(false);
  }

  checkToken(): void {
    this._hasToken.set(this.getToken() !== null);
  }
}
