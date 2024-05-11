import { ChangeDetectionStrategy, Component } from '@angular/core';
import { SharedModule } from '../../shared.module';
import { MaterialModule } from '../../material.module';

@Component({
  selector: 'app-nav-panel',
  standalone: true,
  imports: [SharedModule, MaterialModule],
  templateUrl: './nav-panel.component.html',
  styleUrl: './nav-panel.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class NavPanelComponent {}
