import { Component } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { DivisaoService } from 'src/app/core/services/divisao.service';

@Component({
  selector: 'app-cadastrar-divisao',
  templateUrl: './cadastrar-divisao.component.html',
  styleUrls: ['./cadastrar-divisao.component.css']
})
export class CadastrarDivisaoComponent {

  constructor(
    private divisaoService: DivisaoService,
    private router: Router
  ) {}  

  cadastrarDivisao(formulario: FormGroup) {
    this.divisaoService.cadastrarDivisao(formulario.value).subscribe({
      next: (value) => {
        this.router.navigate(['/listarDivisao']);
      },
      error: (erro) => {
        console.log("Erro ao cadastar a divisão");
      }
    })
    
  }

}
