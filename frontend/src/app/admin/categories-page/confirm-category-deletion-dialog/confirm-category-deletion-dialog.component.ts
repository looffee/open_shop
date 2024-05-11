import { ChangeDetectionStrategy, Component, inject } from '@angular/core';
import { SharedModule } from '../../../shared.module';
import { MaterialModule } from '../../../material.module';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Category } from '../../../models';

export enum ConfirmCategoryDeletionDialogResult {
  DELETE_CATEGORY,
  CANCEL_DELETION,
}

@Component({
  standalone: true,
  templateUrl: './confirm-category-deletion-dialog.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
  imports: [SharedModule, MaterialModule],
})
export class ConfirmDialogDeletionDialogComponent {
  readonly resultEnum = ConfirmCategoryDeletionDialogResult;
  readonly data = inject<Category>(MAT_DIALOG_DATA);
}
