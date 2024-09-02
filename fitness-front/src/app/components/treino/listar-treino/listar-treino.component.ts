import { HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Paginacao } from 'src/app/core/models/Paginacao';
import { Treino } from 'src/app/core/models/Treino';
import { TreinoService } from 'src/app/core/services/treino.service';

@Component({
  selector: 'app-listar-treino',
  templateUrl: './listar-treino.component.html',
  styleUrls: ['./listar-treino.component.css']
})
export class ListarTreinoComponent implements OnInit{
  treinos: Treino[] = [];
  paginacao: Paginacao;
  
  constructor(private treinoService: TreinoService) {}
  
  ngOnInit(): void {
    this.listarTreinosPaginacao(new HttpParams());

  }

  listarTreinos() {
    this.treinoService.listarTreinos().subscribe({
      next: (treinos) => {
        this.treinos = treinos;
      },
      error: (erro) => console.log("Erro ao listar os treinos " + erro)
    })
  }

  listarTreinosPaginacao(parametros: HttpParams){
    this.treinoService.listarTreinosPaginacao(parametros).subscribe({
      next: (paginacao) => {
        this.paginacao = paginacao;
        this.treinos = <Treino[]> paginacao.content;
      },
      error: (erro) => console.log("Erro ao listar os treinos por paginação " + erro)
    })
  }
  

}
