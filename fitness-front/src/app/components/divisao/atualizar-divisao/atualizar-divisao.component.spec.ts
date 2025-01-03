import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AtualizarDivisaoComponent } from './atualizar-divisao.component';

describe('AtualizarDivisaoComponent', () => {
  let component: AtualizarDivisaoComponent;
  let fixture: ComponentFixture<AtualizarDivisaoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AtualizarDivisaoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AtualizarDivisaoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
