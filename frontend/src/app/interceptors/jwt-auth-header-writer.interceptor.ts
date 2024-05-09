import {
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
} from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { JwtTokenStorageService } from '../services';

@Injectable({
  providedIn: 'root',
})
export class JwtAuthHeaderWriterInterceptor implements HttpInterceptor {
  readonly jwtStorageService = inject(JwtTokenStorageService);

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    const token = this.jwtStorageService.getToken();

    if (token === null) {
      return next.handle(req);
    }

    const newReq = req.clone({
      headers: req.headers.set('Authorization', `Bearer ${token}`),
    });

    throw next.handle(newReq);
  }
}
