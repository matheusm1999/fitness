import { HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Paginacao, Paginador } from 'src/app/core/models/Paginacao';
import { Pessoa } from 'src/app/core/models/Pessoa';
import { PessoaService } from 'src/app/core/services/pessoa.service';

@Component({
  selector: 'app-listar-pessoa',
  templateUrl: './listar-pessoa.component.html',
  styleUrls: ['./listar-pessoa.component.css'],
})
export class ListarPessoaComponent implements OnInit{
  pessoas: Pessoa[] = [];
  paginacao: Paginacao;
  paginador: Paginador = new Paginador();

  constructor(private pessoaService: PessoaService) {}

  ngOnInit(): void {
    this.listarPessoasPaginacao(new HttpParams());
  }

  listarPessoas() {
    this.pessoaService.listarPessoas().subscribe({
      next: (pessoas) => {
        this.pessoas = pessoas;
      },
      error: (erro) => console.log('Erro ao listar as pessoas'),
    });
  }

  listarPessoasPaginacao(parametros: HttpParams) {
    this.pessoaService.listarPessoasPaginacao(parametros).subscribe({
      next: (paginacao: Paginacao) => {
        console.log(paginacao);
        this.paginacao = paginacao;
        this.pessoas = <Pessoa[]>paginacao.content;
      },
      error: (erro) => console.log('Erro ao listar as pessoas'),
    });
  }

  excluirPessoa(id: number) {
    let confirmacao = confirm('Tem certeza que deseja excluir a pessoa?');
    if (confirmacao) {
      this.pessoaService.excluirPessoa(id).subscribe({
        error: (erro) => console.log('Erro ao excluir a pessoa'),
      });
    }
  }

  atualizarQuantidadeRegistrosPaginacao(quantidadeRegistros: number) {
    this.paginador.tamanhoPagina = quantidadeRegistros;
    const params = new HttpParams().set('tamanhoPagina', this.paginador.tamanhoPagina);

    this.listarPessoasPaginacao(params);
  }

  avancarPagina() {
    this.paginador.pagina += 1;

    const params = new HttpParams()
      .set('pagina', this.paginador.pagina)
      .set('tamanhoPagina', this.paginador.tamanhoPagina);

    this.listarPessoasPaginacao(params);
  }

  retrocederPagina() {
    this.paginador.pagina -= 1;

    const params = new HttpParams()
      .set('pagina', this.paginador.pagina)
      .set('tamanhoPagina', this.paginador.tamanhoPagina);

    this.listarPessoasPaginacao(params);
  }
}
