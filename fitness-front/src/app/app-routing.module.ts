import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { ListarPessoaComponent } from './components/pessoa/listar-pessoa/listar-pessoa.component';
import { CadastrarPessoaComponent } from './components/pessoa/cadastrar-pessoa/cadastrar-pessoa.component';
import { AtualizarPessoaComponent } from './components/pessoa/atualizar-pessoa/atualizar-pessoa.component';
import { ListarTreinoComponent } from './components/treino/listar-treino/listar-treino.component';
import { CadastrarTreinoComponent } from './components/treino/cadastrar-treino/cadastrar-treino.component';
import { AtualizarTreinoComponent } from './components/treino/atualizar-treino/atualizar-treino.component';
import { ListarDivisaoComponent } from './components/divisao/listar-divisao/listar-divisao.component';
import { CadastrarDivisaoComponent } from './components/divisao/cadastrar-divisao/cadastrar-divisao.component';
import { AtualizarDivisaoComponent } from './components/divisao/atualizar-divisao/atualizar-divisao.component';

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
  },
  {
    path:'listarTreino',
    component: ListarTreinoComponent
  },
  {
    path:'cadastrarTreino',
    component: CadastrarTreinoComponent
  },
  {
    path:'atualizarTreino/:id',
    component: AtualizarTreinoComponent
  },
  {
    path:'listarDivisao',
    component: ListarDivisaoComponent
  },
  {
    path:'cadastrarDivisao',
    component: CadastrarDivisaoComponent
  },
    {
    path:'atualizarDivisao/:id',
    component: AtualizarDivisaoComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
