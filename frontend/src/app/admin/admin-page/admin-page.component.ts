import { Component } from '@angular/core';
import { SharedModule } from '../../shared.module';
import { MaterialModule } from '../../material.module';

import { AgGridAngular } from 'ag-grid-angular'; // Angular Data Grid Component
import { ColDef } from 'ag-grid-community'; // Column Definition Type Interface
import { RouterModule } from '@angular/router';
import { CategoriesPageComponent } from '../categories-page/categories-page.component';

@Component({
  selector: 'app-admin-page',
  standalone: true,
  imports: [SharedModule, MaterialModule, AgGridAngular],
  templateUrl: './admin-page.component.html',
  styleUrl: './admin-page.component.scss',
})
export class AdminPageComponent {}
