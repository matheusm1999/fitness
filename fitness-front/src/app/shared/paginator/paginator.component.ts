import { Component, EventEmitter, Input, OnChanges, Output, SimpleChanges } from '@angular/core';
import { Paginacao } from 'src/app/core/models/Paginacao';

@Component({
  selector: 'app-paginator',
  templateUrl: './paginator.component.html',
  styleUrls: ['./paginator.component.css'],
})
export class PaginatorComponent{
  @Output() acaoClique: EventEmitter<any> = new EventEmitter();
  @Output() acaoAvancarPagina: EventEmitter<any> = new EventEmitter();
  @Output() acaoRetrocederPagina: EventEmitter<any> = new EventEmitter();
  @Input() paginacao: Paginacao;


  trocarQuantidadeRegistrosPagina(evento) {
    this.acaoClique.emit(evento);
  }

  retrocederPagina() {
    this.acaoRetrocederPagina.emit();
  }

  avancarPagina() {
    this.acaoAvancarPagina.emit();
  }

}
