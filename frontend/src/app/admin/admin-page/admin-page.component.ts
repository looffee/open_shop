import { Component } from '@angular/core';
import { SharedModule } from '../../shared.module';
import { MaterialModule } from '../../material.module';

import { AgGridAngular } from 'ag-grid-angular';
import { NavPanelComponent } from '../nav-panel/nav-panel.component';

@Component({
  selector: 'app-admin-page',
  standalone: true,
  imports: [SharedModule, MaterialModule, AgGridAngular, NavPanelComponent],
  templateUrl: './admin-page.component.html',
  styleUrl: './admin-page.component.scss',
})
export class AdminPageComponent {}
