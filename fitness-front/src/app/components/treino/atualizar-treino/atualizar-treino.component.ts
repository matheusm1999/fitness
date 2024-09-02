import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { Treino } from 'src/app/core/models/Treino';
import { TreinoService } from 'src/app/core/services/treino.service';

@Component({
  selector: 'app-atualizar-treino',
  templateUrl: './atualizar-treino.component.html',
  styleUrls: ['./atualizar-treino.component.css']
})
export class AtualizarTreinoComponent implements OnInit{
  treino: Treino;
  mensagemErro: string;

  constructor(
    private treinoService:TreinoService,
    private router:Router,
    private route:ActivatedRoute
  ) {}
  
  ngOnInit(): void {
    this.treinoService.getTreino(parseInt(this.route.snapshot.paramMap.get('id'))).subscribe({
      next: (treino) => {
        console.log(treino);
        this.treino = treino;
      }
    })
  }

  atualizarTreino(formulario: FormGroup): void {
    console.log(formulario.value);
    let treinoAtualizado = <Treino> formulario.value;
    treinoAtualizado.idTreino = this.treino.idTreino;
    this.treinoService.atualizarTreino(treinoAtualizado).subscribe({
      next: (value) => {
        this.router.navigate(['/listarTreino'])
      },
      error: (erro) => {
        this.mensagemErro = "Erro ao atualizar treino: " + erro.message;
        console.log("Erro ao atualizar o treino");
      }
    })
  }

}
