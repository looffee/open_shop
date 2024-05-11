import {
  Component,
  inject,
  afterNextRender,
  ElementRef,
  ViewChild,
  signal,
  effect,
  ViewContainerRef,
  NgZone,
} from '@angular/core';

import {
  ColDef,
  createGrid,
  GridApi,
  ICellRendererParams,
  SelectionChangedEvent,
} from 'ag-grid-community';
import { CategoryService } from '../../services';
import {
  CreateCategoryFormComponent,
  FormSubmitEvent,
} from '../create-category-form/create-category-form.component';
import { finalize } from 'rxjs';
import { SharedModule } from '../../shared.module';
import { MaterialModule } from '../../material.module';
import { ActionsCellRenderer } from './actions-cell-renderer.component';
import { MatDialog } from '@angular/material/dialog';
import {
  ConfirmCategoryDeletionDialogResult,
  ConfirmDialogDeletionDialogComponent,
} from './confirm-category-deletion-dialog/confirm-category-deletion-dialog.component';
import { Category } from '../../models';

@Component({
  selector: 'app-categories-page',
  standalone: true,
  imports: [
    CreateCategoryFormComponent,
    SharedModule,
    MaterialModule,
    ActionsCellRenderer,
    ConfirmDialogDeletionDialogComponent,
  ],
  templateUrl: './categories-page.component.html',
  styleUrl: './categories-page.component.scss',
})
export class CategoriesPageComponent {
  readonly categoryService = inject(CategoryService);
  readonly vcr = inject(ViewContainerRef);
  readonly dialogService = inject(MatDialog);
  readonly zone = inject(NgZone);

  @ViewChild('gridContainer')
  readonly gridContainer: ElementRef<HTMLElement> | null = null;
  @ViewChild(CreateCategoryFormComponent, { static: true })
  readonly createCategoryFormRef: CreateCategoryFormComponent | null = null;

  readonly loading = signal<boolean>(false);
  readonly error = signal<any>(null);
  readonly categories = this.categoryService.categories;

  grid: GridApi | null = null;

  readonly gridHeight = window.innerHeight * 0.8;

  constructor() {
    this.categoryService.getAllCategories().subscribe(() => null);

    afterNextRender(() => {
      const container = this.gridContainer?.nativeElement;

      if (container == undefined) {
        throw new Error('Grid container not found');
      }

      const colDefs: ColDef[] = [
        { field: 'id', filter: true },
        { field: 'name', filter: true },
        { field: 'description', filter: true },
        { field: 'parentCategoryId', filter: true },
        {
          headerName: 'Actions',
          cellRenderer: (params: ICellRendererParams<any, any, any>) => {
            const compRef = this.vcr.createComponent(ActionsCellRenderer);

            compRef.instance.agInit(params);
            compRef.instance.onDelete = (categoryId: number) => {
              this.deleteCategory(categoryId);
            };
            compRef.changeDetectorRef.detectChanges();

            return compRef.location.nativeElement as HTMLElement;
          },
        },
      ];

      this.grid = createGrid(container, {
        columnDefs: colDefs,
        rowData: this.categories(),
        suppressScrollOnNewData: true,
        rowSelection: 'multiple',
        onSelectionChanged: (event: SelectionChangedEvent<Category>) => {},
      });
    });

    effect(() => {
      const categories = this.categories();

      if (this.grid == null) {
        throw new Error('Grid not initialized');
      }

      this.grid.setGridOption('rowData', categories);
    });
  }

  deleteCategory(categoryId: number): void {
    const dialogRef = this.dialogService.open<
      ConfirmDialogDeletionDialogComponent,
      Category,
      ConfirmCategoryDeletionDialogResult
    >(ConfirmDialogDeletionDialogComponent, {
      data: this.categories().find(({ id }) => id === categoryId),
    });

    dialogRef.componentRef?.changeDetectorRef.detectChanges();

    dialogRef.afterClosed().subscribe((result) => {
      if (result === ConfirmCategoryDeletionDialogResult.DELETE_CATEGORY) {
        this.categoryService.deleteCategory(categoryId).subscribe(() => null);
      }
    });
  }

  createCategory(event: FormSubmitEvent): void {
    this.loading.set(true);
    this.error.set(null);

    this.categoryService
      .createCategory(event)
      .pipe(finalize(() => this.loading.set(false)))
      .subscribe({
        next: (category) => {
          this.createCategoryFormRef?.resetForm();
        },
        error: (error) => {
          this.error.set(error);
        },
      });
  }
}
