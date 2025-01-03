import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Divisao } from 'src/app/core/models/Divisao';
import { DivisaoService } from 'src/app/core/services/divisao.service';

@Component({
  selector: 'app-atualizar-divisao',
  templateUrl: './atualizar-divisao.component.html',
  styleUrls: ['./atualizar-divisao.component.css']
})
export class AtualizarDivisaoComponent implements OnInit{
  divisao: Divisao;

  constructor(
    private divisaoService: DivisaoService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.divisaoService.getDivisao(parseInt(this.route.snapshot.paramMap.get('id'))).subscribe({
      next: (divisao) => {
        this.divisao = divisao;
      },
      error: (erro) => {
        console.log("Erro ao atualizar a divisão");
      }
    })
  }

  editarDivisao(formulario: FormGroup) {
    let divisaoAtualizada = <Divisao> formulario.value;
    divisaoAtualizada.idDivisao = this.divisao.idDivisao;
    this.divisaoService.atualizarDivisao(formulario.value).subscribe({
      next: () => {
        this.router.navigate(['/listarDivisao']);
      },
      error: (erro) => {
        console.log("Erro ao atualizar a divisão");
      }
    })
  }

}
