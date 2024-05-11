import { Component, inject } from '@angular/core';
import { BrandService, CategoryService } from '../services';
import { Brand, Category } from '../models';
import { SharedModule } from '../shared.module';
import { MaterialModule } from '../material.module';

@Component({
  selector: 'app-root-page',
  standalone: true,
  imports: [SharedModule, MaterialModule],
  templateUrl: './root-page.component.html',
  styleUrl: './root-page.component.scss',
})
export class RootPageComponent {
  readonly categoryService = inject(CategoryService);

  categories: Category[] = [];

  constructor() {
    this.categoryService.getRootCategories().subscribe((categories) => {
      this.categories = categories;
    });
  }
}
