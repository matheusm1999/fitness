import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CadastrarTreinoComponent } from './cadastrar-treino.component';

describe('CadastrarTreinoComponent', () => {
  let component: CadastrarTreinoComponent;
  let fixture: ComponentFixture<CadastrarTreinoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CadastrarTreinoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CadastrarTreinoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
