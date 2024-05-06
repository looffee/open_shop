import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';

const components = [HeaderComponent, FooterComponent];

const modules = [CommonModule, FormsModule, ReactiveFormsModule, RouterModule];

@NgModule({
  imports: modules,
  exports: [...modules, ...components],
  declarations: [...components],
  providers: [],
})
export class SharedModule {}
