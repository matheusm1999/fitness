import { Component, Input } from '@angular/core';
import { Pessoa } from 'src/app/core/models/Pessoa';

@Component({
  selector: 'app-pessoa-single',
  templateUrl: './pessoa-single.component.html',
  styleUrls: ['./pessoa-single.component.css']
})
export class PessoaSingleComponent {
  @Input() pessoa: Pessoa;

}
