import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormularioBaseDivisaoComponent } from './formulario-base-divisao.component';

describe('FormularioBaseDivisaoComponent', () => {
  let component: FormularioBaseDivisaoComponent;
  let fixture: ComponentFixture<FormularioBaseDivisaoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormularioBaseDivisaoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormularioBaseDivisaoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
