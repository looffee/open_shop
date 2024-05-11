import {
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
} from '@angular/common/http';
import { isPlatformBrowser } from '@angular/common';
import { Observable } from 'rxjs';
import { JwtTokenStorageService } from '../services';
import { Injectable, PLATFORM_ID, inject } from '@angular/core';

@Injectable()
export class JwtAuthHeaderWriterInterceptor implements HttpInterceptor {
  readonly jwtStorageService = inject(JwtTokenStorageService);
  readonly platformId = inject(PLATFORM_ID);

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    if (isPlatformBrowser(this.platformId) === false) {
      return next.handle(req);
    }

    const token = this.jwtStorageService.getToken();

    if (token === null) {
      return next.handle(req);
    }

    const newReq = req.clone({
      headers: req.headers.set('Authorization', `Bearer ${token}`),
    });

    return next.handle(newReq);
  }
}
