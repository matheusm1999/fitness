import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormularioBaseTreinoComponent } from './formulario-base-treino.component';

describe('FormularioBaseTreinoComponent', () => {
  let component: FormularioBaseTreinoComponent;
  let fixture: ComponentFixture<FormularioBaseTreinoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormularioBaseTreinoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormularioBaseTreinoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
