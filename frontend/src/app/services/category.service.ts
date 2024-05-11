import { HttpClient } from '@angular/common/http';
import { Injectable, Signal, computed, inject, signal } from '@angular/core';
import { Observable, tap, of, map } from 'rxjs';
import { Category } from '../models';

interface CreateCategory {
  name: string;
  description: string;
  parentCategoryId: number | null;
}

@Injectable()
export class CategoryService {
  readonly http = inject(HttpClient);

  private readonly _categories = signal<Category[]>([]);
  readonly categories: Signal<Category[]> = this._categories;

  readonly rootCategories: Signal<Category[]> = computed(() => {
    return this._categories().filter(
      (category) => category.parentCategoryId == null
    );
  });

  getRootCategories(): Observable<Category[]> {
    if (this.rootCategories().length > 0) {
      return of(this.rootCategories());
    }

    return this.http.get<Category[]>('/category/get-root').pipe(
      tap((rootCategories) => {
        this._categories.update((cts) => {
          const rootCategoriesIds = rootCategories.map(({ id }) => id);
          const filteredCategories = cts.filter(
            ({ id }) => !rootCategoriesIds.includes(id)
          );

          return [...filteredCategories, ...rootCategories];
        });
      })
    );
  }

  getAllCategories(): Observable<Category[]> {
    if (this._categories().length > 0) {
      return of(this._categories());
    }

    return this.http.get<Category[]>('/category/get-all').pipe(
      tap((categories) => {
        this._categories.set(categories);
      })
    );
  }

  createCategory(category: CreateCategory): Observable<Category> {
    return this.http.post<Category>('/category/create', category).pipe(
      tap((newCategory) => {
        this._categories.set([...this._categories(), newCategory]);
      })
    );
  }

  deleteCategory(categoryId: number): Observable<void> {
    return this.http.delete(`/category/delete/${categoryId}`).pipe(
      tap(() => {
        this._categories.update((current) => {
          return current.filter(({ id }) => id !== categoryId);
        });
      }),
      map(() => undefined)
    );
  }
}
