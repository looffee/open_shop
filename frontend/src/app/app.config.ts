import { ApplicationConfig, inject } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { provideClientHydration } from '@angular/platform-browser';
import { API_SERVER_URL } from './providers/api-server-url';
import {
  HTTP_INTERCEPTORS,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
  provideHttpClient,
  withFetch,
  withInterceptors,
  withInterceptorsFromDi,
} from '@angular/common/http';

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes),
    provideClientHydration(),
    {
      provide: API_SERVER_URL,
      useValue: 'http://localhost:8080/api',
    },
    {
      provide: HTTP_INTERCEPTORS,
      useFactory: (appServerUrl: string): HttpInterceptor => {
        console.log('intercepted request', appServerUrl);

        return {
          intercept(req, next) {
            const apiReq = req.clone({ url: `${appServerUrl}${req.url}` });
            return next.handle(apiReq);
          },
        };
      },
      deps: [API_SERVER_URL],
      multi: true,
    },
    provideHttpClient(withFetch(), withInterceptorsFromDi()),
  ],
};
