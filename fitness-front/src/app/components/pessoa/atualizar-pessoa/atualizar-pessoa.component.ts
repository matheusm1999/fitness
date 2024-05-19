import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Pessoa } from 'src/app/core/models/Pessoa';
import { PessoaService } from 'src/app/core/services/pessoa.service';

@Component({
  selector: 'app-atualizar-pessoa',
  templateUrl: './atualizar-pessoa.component.html',
  styleUrls: ['./atualizar-pessoa.component.css']
})
export class AtualizarPessoaComponent implements OnInit {
  pessoa: Pessoa;
  mensagemErro: string;

  constructor(
    private pessoaService: PessoaService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.pessoaService.getPessoa(parseInt(this.route.snapshot.paramMap.get('id'))).subscribe(
      resultado => this.pessoa = resultado
    );
  }

  atualizarPessoa(formulario: FormGroup) {
    if(!formulario.valid){
      return;
    }
    let pessoaAtualizada: Pessoa = formulario.value;
    pessoaAtualizada.idPessoa = 79;
    this.pessoaService.atualizarPessoa(formulario.value).subscribe({
      next: (value) => {
        this.router.navigate(['/listarPessoa']);
      },
      error: (err) => {
        console.log(err);
        this.mensagemErro = '';
        this.mensagemErro = err.error
      }
    });
  }

}