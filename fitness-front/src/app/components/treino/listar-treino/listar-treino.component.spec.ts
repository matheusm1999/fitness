import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListarTreinoComponent } from './listar-treino.component';

describe('ListarTreinoComponent', () => {
  let component: ListarTreinoComponent;
  let fixture: ComponentFixture<ListarTreinoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListarTreinoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListarTreinoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
