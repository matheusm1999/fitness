import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { ListarPessoaComponent } from './components/pessoa/listar-pessoa/listar-pessoa.component';
import { CadastrarPessoaComponent } from './components/pessoa/cadastrar-pessoa/cadastrar-pessoa.component';
import { AtualizarPessoaComponent } from './components/pessoa/atualizar-pessoa/atualizar-pessoa.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: '/home',
    pathMatch: 'full'
  },
  {
    path: 'home',
    component: HomeComponent
  },
  {
    path:'listarPessoa',
    component: ListarPessoaComponent
  },
  {
    path:'cadastrarPessoa',
    component: CadastrarPessoaComponent
  },
  {
    path:'atualizarPessoa/:id',
    component: AtualizarPessoaComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
