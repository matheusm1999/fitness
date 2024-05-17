import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { PessoaService } from 'src/app/core/services/pessoa.service';

@Component({
  selector: 'app-cadastrar-pessoa',
  templateUrl: './cadastrar-pessoa.component.html',
  styleUrls: ['./cadastrar-pessoa.component.css'],
})
export class CadastrarPessoaComponent implements OnInit{
  formulario: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private pessoaService: PessoaService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.formulario = this.formBuilder.group({
      nome: [''],
      login: [''],
      senha: [''],
    });
  }

  cadastrarPessoa() {
    this.pessoaService.cadastrarPessoa(this.formulario.value).subscribe();
    this.router.navigate(['/listarPessoa']);
  }
}
