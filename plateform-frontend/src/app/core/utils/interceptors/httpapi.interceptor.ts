import { Injectable } from '@angular/core';
import {
  HttpInterceptor,
  HttpRequest,
  HttpResponse,
  HttpHandler,
  HttpEvent,
  HttpErrorResponse,
} from '@angular/common/http';

import { Observable, throwError } from 'rxjs';
import { map, catchError } from 'rxjs/operators';
import { AuthService } from '../../services/auth.service';
@Injectable()
export class HttpApiInterceptor implements HttpInterceptor {
  constructor(private authService: AuthService) {}
  intercept(
    request: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    const token = this.authService.getJwtToken();
    if (token) {
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`,
        },
      });
    }

    if (!request.headers.has('Content-Type')) {
      if (!(request.body instanceof FormData)) {
        request = request.clone({
          headers: request.headers.set('Content-Type', 'application/json'),
        });
      }
    }
    request = request.clone({
      headers: request.headers.set('Accept', 'application/json'),
    });

    return next.handle(request).pipe(
      map((event: HttpEvent<any>) => {
        if (event instanceof HttpResponse) {
          console.log('event--->>>', event);
          // this.errorDialogService.openDialog(event);
        }
        return event;
      }),
      catchError((error: HttpErrorResponse) => {
        let data = {};
        console.log(error);
        data = {
          reason: error && error.error.reason ? error.error.reason : '',
          status: error.status,
        };
        console.log(data);
        return throwError(error);
      })
    );
  }
}
