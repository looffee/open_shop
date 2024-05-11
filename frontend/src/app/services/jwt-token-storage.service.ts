import {
  Injectable,
  PLATFORM_ID,
  Signal,
  computed,
  inject,
  signal,
} from '@angular/core';
import { isPlatformBrowser } from '@angular/common';

@Injectable()
export class JwtTokenStorageService {
  private TOKEN_KEY = 'jwt-token';
  readonly platformId = inject(PLATFORM_ID);

  private readonly _token = signal<string | null>(null);
  readonly token: Signal<string | null> = this._token;
  readonly hasToken: Signal<boolean> = computed(() => this.token() !== null);

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
    this._token.set(token);
  }

  removeToken(): void {
    this.storage.removeItem(this.TOKEN_KEY);
    this._token.set(null);
  }

  checkToken(): void {
    this._token.set(this.getToken());
  }
}
