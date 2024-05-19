import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Pessoa } from 'src/app/core/models/Pessoa';
import { PessoaService } from 'src/app/core/services/pessoa.service';

@Component({
  selector: 'app-formulario-base',
  templateUrl: './formulario-base.component.html',
  styleUrls: ['./formulario-base.component.css']
})
export class FormularioBaseComponent implements OnInit {
  @Output() acaoClique: EventEmitter<any> = new EventEmitter<any>;
  @Input() pessoa: Pessoa;
  @Input() conteudoBotao: string = '';
  @Input() titulo: string = '';
  formulario: FormGroup;

  constructor(
    private formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
    this.formulario = this.formBuilder.group({
      nome: ['', Validators.required],
      login: ['', Validators.required],
      senha: ['', Validators.required],
    });
    
    if(this.pessoa != null){
      this.formulario.get('nome').setValue(this.pessoa.nome);
      this.formulario.get('login').setValue(this.pessoa.login);
      this.formulario.get('senha').setValue(this.pessoa.senha);
    }
     
  }

  acao() {
    this.acaoClique.emit(this.formulario);
  }

}
