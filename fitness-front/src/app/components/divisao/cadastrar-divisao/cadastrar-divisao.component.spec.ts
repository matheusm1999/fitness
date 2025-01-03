import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CadastrarDivisaoComponent } from './cadastrar-divisao.component';

describe('CadastrarDivisaoComponent', () => {
  let component: CadastrarDivisaoComponent;
  let fixture: ComponentFixture<CadastrarDivisaoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CadastrarDivisaoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CadastrarDivisaoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
