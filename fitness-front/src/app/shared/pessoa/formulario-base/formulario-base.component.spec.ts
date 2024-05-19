import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormularioBaseComponent } from './formulario-base.component';

describe('FormularioBaseComponent', () => {
  let component: FormularioBaseComponent;
  let fixture: ComponentFixture<FormularioBaseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormularioBaseComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormularioBaseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
