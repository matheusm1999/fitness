import { HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Divisao } from 'src/app/core/models/Divisao';
import { Paginacao, Paginador } from 'src/app/core/models/Paginacao';
import { DivisaoService } from 'src/app/core/services/divisao.service';

@Component({
  selector: 'app-listar-divisao',
  templateUrl: './listar-divisao.component.html',
  styleUrls: ['./listar-divisao.component.css']
})
export class ListarDivisaoComponent implements OnInit{
  constructor(private divisaoService: DivisaoService) {}
  divisoes: Divisao[] = [];
  paginacao: Paginacao;
  paginador: Paginador = new Paginador();

  ngOnInit(): void {
    this.listarDivisoesPaginacao(new HttpParams());
  }

  listarDivisoes(HttpParams : HttpParams) {
    this.divisaoService.listarDivisoes(HttpParams).subscribe({
      next: (divisoes) => {
        this.divisoes = divisoes;
      },
      error: (erro) => {
        console.log("Erro ao listar as divisões ", erro);
      }
    })
  }

  listarDivisoesPaginacao(parametros: HttpParams){
    this.divisaoService.listarDivisoesPaginacao(parametros).subscribe({
      next: (paginacao) => {
        this.paginacao = paginacao;
        this.divisoes = <Divisao[]> paginacao.content;
      },
      error: (erro) => console.log("Erro ao listar as divisões por paginação ", erro)
    })
  }

  excluirDivisao(id: number){
    this.divisaoService.excluirDivisao(id).subscribe({
      next: (divisao) => {
        this.divisoes.forEach((divisao, indice, divisoes) => {
          if(divisao.idDivisao === id) {
            divisoes.splice(indice,1);
          }
        })
        console.log("Divisão removida com sucesso");
      },
      error: (erro) => {
        console.log("Erro ao excluir a Divisão");
      }
    })
  }

  atualizarQuantidadeRegistrosPaginacao(quantidadeRegistros: number) {
    this.paginador.tamanhoPagina = quantidadeRegistros;
    const params = new HttpParams().set('tamanhoPagina', this.paginador.tamanhoPagina);

    this.listarDivisoesPaginacao(params);

  }

  retrocederPagina() {
    this.paginador.pagina -= 1;

    const params = new HttpParams()
      .set('pagina',this.paginador.pagina)
      .set('tamanhoPagina', this.paginador.tamanhoPagina)

    this.listarDivisoesPaginacao(params);
  }

  avancarPagina() {
    this.paginador.pagina += 1;

    const params = new HttpParams()
      .set('pagina',this.paginador.pagina)
      .set('tamanhoPagina', this.paginador.tamanhoPagina)

    this.listarDivisoesPaginacao(params);
  }

}
