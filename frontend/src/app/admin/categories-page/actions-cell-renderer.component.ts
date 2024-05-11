import { Component } from '@angular/core';
import { ICellRendererAngularComp } from 'ag-grid-angular';
import { ICellRendererParams } from 'ag-grid-community'; // Column Definition Type Interface
import { SharedModule } from '../../shared.module';
import { MaterialModule } from '../../material.module';
import { Category } from '../../models';

@Component({
  standalone: true,
  imports: [SharedModule, MaterialModule],
  template: `
    <div class="flex flex-1 flex-row gap-4 justify-center items-center">
      <button type="button" mat-button color="primary" (click)="delete()">
        Delete
      </button>
    </div>
  `,
  styles: `
    :host {
      display: flex;
      flex-direction: column;
      height: 100%;
    }
  `,
})
export class ActionsCellRenderer implements ICellRendererAngularComp {
  params: ICellRendererParams<Category, any, any> | null = null;

  onDelete: ((categoryId: number) => void) | null = null;

  agInit(params: ICellRendererParams<any, any, any>): void {
    this.params = params;
  }

  destroy?(): void {
    return;
  }

  delete() {
    if (this.params === null) {
      throw new Error('Params not found');
    }

    if (this.params.data === null || this.params.data === undefined) {
      throw new Error('Data not found');
    }

    this.onDelete?.(this.params.data.id);
  }

  refresh(params: ICellRendererParams<any, any, any>): boolean {
    return true;
  }
}
