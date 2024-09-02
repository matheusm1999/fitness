import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { TreinoService } from 'src/app/core/services/treino.service';

@Component({
  selector: 'app-cadastrar-treino',
  templateUrl: './cadastrar-treino.component.html',
  styleUrls: ['./cadastrar-treino.component.css']
})
export class CadastrarTreinoComponent implements OnInit{
  formBuilder: FormBuilder;
  mensagemErro: string;

  constructor(
    private treinoService: TreinoService,
    private router: Router
  ) {}
  
  ngOnInit(): void {
    
  }
  
  cadastrarTreino(formulario: FormGroup) {
    this.treinoService.cadastrarTreino(formulario.value).subscribe({
      next: (value) => {
        this.router.navigate(['/listarTreino']);
      },
      error: (erro) => {
        this.mensagemErro = "Erro ao inserir treino: " + erro.message;
        console.log("Erro ao salvar o treino " + erro);
      }
    })
  }

}
