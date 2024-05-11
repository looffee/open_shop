import {
  Component,
  ViewChild,
  afterNextRender,
  computed,
  effect,
  inject,
  input,
  model,
  output,
} from '@angular/core';
import { SharedModule } from '../../shared.module';
import { MaterialModule } from '../../material.module';
import { FormBuilder, Validators } from '@angular/forms';
import { Category } from '../../models';
import {
  MatAutocomplete,
  MatAutocompleteSelectedEvent,
} from '@angular/material/autocomplete';

export interface FormSubmitEvent {
  name: string;
  description: string;
  parentCategoryId: number | null;
}

@Component({
  selector: 'app-create-category-form',
  standalone: true,
  imports: [SharedModule, MaterialModule],
  templateUrl: './create-category-form.component.html',
  styleUrl: './create-category-form.component.scss',
})
export class CreateCategoryFormComponent {
  readonly fb = inject(FormBuilder);
  readonly form = this.fb.group({
    name: this.fb.nonNullable.control('', [Validators.required]),
    description: this.fb.nonNullable.control(''),
    parentCategoryId: this.fb.control<number | null>(null),
  });

  @ViewChild('auto', { read: MatAutocomplete })
  readonly autocompleteRef: MatAutocomplete | null = null;

  readonly parentCategories = input<Category[]>([]);
  readonly loading = input<boolean>(false);

  readonly autocompleteModel = model<string>('');
  readonly filteredCategories = computed(() => {
    return this.parentCategories().filter((category) => {
      return category.name
        .toLowerCase()
        .includes(this.autocompleteModel() || '');
    });
  });

  readonly formSubmit = output<FormSubmitEvent>();

  constructor() {
    effect(() => {
      if (this.loading()) {
        this.form.disable();
      } else {
        this.form.enable();
      }
    });

    afterNextRender(() => {
      if (this.autocompleteRef === null) {
        throw new Error('Autocomplete reference not found');
      }

      this.autocompleteRef.displayWith = (categoryId: number) => {
        return (
          this.parentCategories().find((category) => category.id === categoryId)
            ?.name || 'NULL'
        );
      };
    });
  }

  parentCategorySelected(event: MatAutocompleteSelectedEvent) {
    this.form.controls.parentCategoryId.setValue(event.option.value);
  }

  submit(): void {
    if (this.form.invalid) {
      throw new Error('Form is invalid');
    }

    const value = this.form.value;

    if (!this.isValidFormSubmitEvent(value)) {
      throw new Error('Invalid form submit event');
    }

    this.formSubmit.emit(value);
  }

  isValidFormSubmitEvent(event: any): event is FormSubmitEvent {
    return (
      typeof event.name === 'string' &&
      typeof event.description === 'string' &&
      (typeof event.parentCategoryId === 'number' ||
        event.parentCategoryId === null)
    );
  }

  resetForm() {
    this.form.reset();
    this.autocompleteModel.set('');
  }
}
