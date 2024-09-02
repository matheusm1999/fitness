import { HttpParams } from '@angular/common/http';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Pessoa } from 'src/app/core/models/Pessoa';
import { Treino } from 'src/app/core/models/Treino';
import { PessoaService } from 'src/app/core/services/pessoa.service';

@Component({
  selector: 'app-formulario-base-treino',
  templateUrl: './formulario-base-treino.component.html',
  styleUrls: ['./formulario-base-treino.component.css']
})
export class FormularioBaseTreinoComponent {

  @Output() acaoClique: EventEmitter<any> = new EventEmitter<any>;
  @Input() treino: Treino;
  @Input() titulo: string = '';
  @Input() conteudoBotao: string = '';
  formulario: FormGroup;
  pessoas: Pessoa[] = [];
  pessoasConteudo: string[] = [];
  inputPessoa:string = '';

  constructor(
    private formBuilder: FormBuilder,
    private pessoaService: PessoaService
  ) {}

  ngOnInit(): void {
    this.pessoasConteudo = [];
    this.pessoaService.listarPessoasPaginacao(null).subscribe({
      next: (pessoas) => {
        this.pessoas = <Pessoa[]>pessoas.content;
        this.pessoas.forEach(pessoa => this.pessoasConteudo.push(pessoa.nome));
      },
      error: (erro) => console.log("Erro ao listar as pessoas")
    })

    this.formulario = this.formBuilder.group({
      nome: ['', Validators.required],
      dataInicio: ['', Validators.required],
      dataFim: ['', Validators.required],
      pessoa: this.formBuilder.group({
        idPessoa: ['', Validators.required]
      })
    });

    if(this.treino != null) {
      this.formulario.get('nome').setValue(this.treino.nome);
      this.formulario.get('dataInicio').setValue(this.treino.dataInicio);
      this.formulario.get('dataFim').setValue(this.treino.dataFim);
      this.formulario.get('pessoa.idPessoa').setValue(this.treino.pessoa.idPessoa);
      this.inputPessoa = this.treino.pessoa.nome;
    }

  }

  acao(){
    this.acaoClique.emit(this.formulario);
  }

  buscarPessoas(event: any) {
    const params: HttpParams = new HttpParams().set('nome',event.value);
    this.pessoasConteudo = [];
    this.pessoaService.listarPessoas(params).subscribe({
      next: (pessoas) => {
        pessoas.forEach(pessoa => this.pessoasConteudo.push(pessoa.nome));
      },
      error: (erro) => {
        console.log(erro);
      }
    });
  }

  selecionarPessoa(indicePessoa: number) {
    const pessoa = this.pessoas.at(indicePessoa);
    this.inputPessoa = pessoa.nome;
    this.formulario.get('pessoa.idPessoa').setValue(pessoa.idPessoa);
    //this.trocarEstadoSelectPessoa();
  }

  trocarEstadoSelectPessoa() {
    const select = document.getElementById("selectPessoa");

    if(select.classList.contains('hidden')){
      select.classList.remove('hidden');
    }else{
      select.classList.add('hidden');
    }
  }

}
