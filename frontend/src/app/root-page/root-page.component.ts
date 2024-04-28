import { Component, inject } from '@angular/core';
import { BrandService } from '../services';
import { Brand } from '../models';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root-page',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './root-page.component.html',
  styleUrl: './root-page.component.scss',
})
export class RootPageComponent {
  brandService = inject(BrandService);

  brands: Brand[] = [];

  constructor() {
    this.brandService.getBrands().subscribe((response) => {
      this.brands = response;
    });
  }
}
