import { Component } from '@angular/core';
import { Pessoa } from 'src/app/core/models/Pessoa';
import { PessoaService } from 'src/app/core/services/pessoa.service';

@Component({
  selector: 'app-listar-pessoa',
  templateUrl: './listar-pessoa.component.html',
  styleUrls: ['./listar-pessoa.component.css'],
})
export class ListarPessoaComponent {
  pessoas: Pessoa[] = [];

  constructor(private pessoaService: PessoaService) {}

  listarPessoas() {
    this.pessoaService.listarPessoas().subscribe({
      next: (pessoas) => {
        this.pessoas = pessoas;
      },
      error: (erro) => console.log('Erro ao listar as pessoas'),
    });
  }
}
