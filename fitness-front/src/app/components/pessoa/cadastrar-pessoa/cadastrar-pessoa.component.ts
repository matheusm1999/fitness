import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { PessoaService } from 'src/app/core/services/pessoa.service';

@Component({
  selector: 'app-cadastrar-pessoa',
  templateUrl: './cadastrar-pessoa.component.html',
  styleUrls: ['./cadastrar-pessoa.component.css'],
})
export class CadastrarPessoaComponent implements OnInit {
  formulario: FormGroup;
  mensagemErro: string;

  constructor(
    private formBuilder: FormBuilder,
    private pessoaService: PessoaService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.formulario = this.formBuilder.group({
      nome: [''],
      login: [''],
      senha: [''],
    });
  }

  cadastrarPessoa(formulario: FormGroup) {
    this.pessoaService.cadastrarPessoa(formulario.value).subscribe({
      next: (value) => {
        this.router.navigate(['/listarPessoa']);
      },
      error: (err) => {
        this.mensagemErro = '';
        //Exibe cada erro de validação retornado pelo backend
        if(err.status == 400) {
          err.error.forEach(erro => {
            this.mensagemErro += erro.mensagem;
          });
        }
        else if(err.status == 409){
          this.mensagemErro = err.error;
        }
        else{
          this.mensagemErro = err.message;
        }
      }
    });
  }
}
