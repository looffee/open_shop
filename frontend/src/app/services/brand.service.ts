import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable, delay } from 'rxjs';
import { Brand } from '../models';

@Injectable({
  providedIn: 'root',
})
export class BrandService {
  readonly http = inject(HttpClient);

  getBrands(): Observable<Brand[]> {
    return this.http.get<Brand[]>('/brand/get-all');
  }
}
