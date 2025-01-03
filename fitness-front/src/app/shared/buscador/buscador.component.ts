import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-buscador',
  templateUrl: './buscador.component.html',
  styleUrls: ['./buscador.component.css']
})
export class BuscadorComponent {
  @Output() acaoDigitar:EventEmitter<any> = new EventEmitter();
  @Output() acaoSelecionarRegistro:EventEmitter<any> = new EventEmitter();
  @Input() textoInput: string = '';
  @Input() registros: any[];
  @Input() placeholder: string = 'Escolha um valor';
  
  buscarDados(evento) {
    this.acaoDigitar.emit(evento);
  }

  selecionarRegistro(registro) {
    this.acaoSelecionarRegistro.emit(registro);
    this.trocarEstadoSelecterRegistros();
  }

  trocarEstadoSelecterRegistros() {
    const select = document.getElementById("selectRegistros");

    if(select.classList.contains('hidden')){
      select.classList.remove('hidden');
    }else{
      select.classList.add('hidden');
    }
  }

}
