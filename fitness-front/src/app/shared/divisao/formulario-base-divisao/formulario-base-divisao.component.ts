import { HttpParams } from '@angular/common/http';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Divisao } from 'src/app/core/models/Divisao';
import { Treino } from 'src/app/core/models/Treino';
import { DivisaoService } from 'src/app/core/services/divisao.service';
import { TreinoService } from 'src/app/core/services/treino.service';
import { DiasSemana } from 'src/app/environment/enviroment.development';

@Component({
  selector: 'app-formulario-base-divisao',
  templateUrl: './formulario-base-divisao.component.html',
  styleUrls: ['./formulario-base-divisao.component.css']
})
export class FormularioBaseDivisaoComponent implements OnInit{
  @Output() acaoClique: EventEmitter<any> = new EventEmitter<any>;
  @Input() conteudoBotao: string = '';
  @Input() titulo: string = '';
  @Input() divisao: Divisao;
  formulario: FormGroup;
  treinos: Treino[] = [];
  treinosConteudo: string[] = [];
  inputTreino: string = '';

  constructor(
    private formBuilder: FormBuilder,
    private divisaoService: DivisaoService,
    private treinoService: TreinoService
  ) {}

  ngOnInit(): void {
    this.listarTreinos(new HttpParams);

    this.formulario = this.formBuilder.group({
      nome: ['', Validators.required],
      diaSemana: ['', Validators.required],
      treino: this.formBuilder.group({
        idTreino: ['', Validators.required]
      })
    })

    if(this.divisao != null) {
      this.formulario.get('nome').setValue(this.divisao.nome);
      this.formulario.get('diaSemana').setValue(DiasSemana[this.divisao.diaSemana]);
      this.formulario.get('treino.idTreino').setValue(this.divisao.treino.idTreino);
      this.inputTreino = this.divisao.treino.nome;
    }

  }

  listarTreinos(parametros: HttpParams) {
    this.treinoService.listarTreinos(parametros).subscribe({
      next: (treinos) => {
        this.treinos = treinos;
        treinos.forEach(treino => this.treinosConteudo.push(treino.nome));
      },
      error: (erro) => {
        console.log("Erro ao listar os treinos", erro);      }
    })
  }

  acao() {
    console.log("FORMULARIO", this.formulario);
    this.acaoClique.emit(this.formulario);
  }

  buscarTreinos(event: any){
    const params: HttpParams = new HttpParams().set('nome',event.value);
    this.treinosConteudo = [];
    this.listarTreinos(params);
  }
  
  selecionarTreino(indiceTreino: number) {
    const treino = this.treinos.at(indiceTreino);
    this.inputTreino = treino.nome;
    this.formulario.get('treino.idTreino').setValue(treino.idTreino);
    //this.trocarEstadoSelectPessoa();
  }

}
