import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AtualizarTreinoComponent } from './atualizar-treino.component';

describe('AtualizarTreinoComponent', () => {
  let component: AtualizarTreinoComponent;
  let fixture: ComponentFixture<AtualizarTreinoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AtualizarTreinoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AtualizarTreinoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
