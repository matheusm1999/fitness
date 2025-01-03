import { HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Paginacao, Paginador } from 'src/app/core/models/Paginacao';
import { Treino } from 'src/app/core/models/Treino';
import { TreinoService } from 'src/app/core/services/treino.service';

@Component({
  selector: 'app-listar-treino',
  templateUrl: './listar-treino.component.html',
  styleUrls: ['./listar-treino.component.css']
})
export class ListarTreinoComponent implements OnInit{
  treinos: Treino[] = [];
  paginacao: Paginacao; //Objeto de paginação que vem do retorno do back-end
  paginador: Paginador = new Paginador(); //Objeto utilizado para troca de dados entre o componente de listar e de paginação
  
  constructor(private treinoService: TreinoService) {}
  
  ngOnInit(): void {
    this.listarTreinosPaginacao(new HttpParams());

  }

  listarTreinos() {
    this.treinoService.listarTreinos(new HttpParams()).subscribe({
      next: (treinos) => {
        this.treinos = treinos;
      },
      error: (erro) => console.log("Erro ao listar os treinos ", erro)
    })
  }

  listarTreinosPaginacao(parametros: HttpParams){
    this.treinoService.listarTreinosPaginacao(parametros).subscribe({
      next: (paginacao) => {
        this.paginacao = paginacao;
        this.treinos = <Treino[]> paginacao.content;
      },
      error: (erro) => console.log("Erro ao listar os treinos por paginação " , erro)
    })
  }

  excluirDivisao(id: number){
    
  }

  atualizarQuantidadeRegistrosPaginacao(quantidadeRegistros: number) {
    this.paginador.tamanhoPagina = quantidadeRegistros;
    const params = new HttpParams().set('tamanhoPagina',this.paginador.tamanhoPagina);

    this.listarTreinosPaginacao(params);
  }
  
  retrocederPagina() {
    this.paginador.pagina -= 1;

    const params = new HttpParams()
      .set('pagina',this.paginador.pagina)
      .set('tamanhoPagina',this.paginador.tamanhoPagina);

    this.listarTreinosPaginacao(params);
  }

  avancarPagina() {
    this.paginador.pagina += 1;

    const params = new HttpParams()
      .set('pagina',this.paginador.pagina)
      .set('tamanhoPagina',this.paginador.tamanhoPagina);

    this.listarTreinosPaginacao(params);
  }

}
