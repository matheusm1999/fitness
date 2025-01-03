import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListarDivisaoComponent } from './listar-divisao.component';

describe('ListarDivisaoComponent', () => {
  let component: ListarDivisaoComponent;
  let fixture: ComponentFixture<ListarDivisaoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListarDivisaoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListarDivisaoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
