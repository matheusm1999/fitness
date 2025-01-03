import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './shared/header/header.component';
import { SidebarComponent } from './shared/sidebar/sidebar.component';
import { FooterComponent } from './shared/footer/footer.component';
import { PessoaComponent } from './pages/pessoa/pessoa.component';
import { HomeComponent } from './pages/home/home.component';
import { ListarPessoaComponent } from './components/pessoa/listar-pessoa/listar-pessoa.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { PessoaSingleComponent } from './components/pessoa/pessoa-single/pessoa-single.component';
import { CadastrarPessoaComponent } from './components/pessoa/cadastrar-pessoa/cadastrar-pessoa.component';
import { FormularioBaseComponent } from './shared/pessoa/formulario-base/formulario-base.component';
import { AtualizarPessoaComponent } from './components/pessoa/atualizar-pessoa/atualizar-pessoa.component';
import { PaginatorComponent } from './shared/paginator/paginator.component';
import { CadastrarTreinoComponent } from './components/treino/cadastrar-treino/cadastrar-treino.component';
import { ListarTreinoComponent } from './components/treino/listar-treino/listar-treino.component';
import { FormularioBaseTreinoComponent } from './shared/treino/formulario-base-treino/formulario-base-treino.component';
import { AtualizarTreinoComponent } from './components/treino/atualizar-treino/atualizar-treino.component';
import { BuscadorComponent } from './shared/buscador/buscador.component';
import { MensagemErroComponent } from './shared/mensagem-erro/mensagem-erro.component';
import { ListarDivisaoComponent } from './components/divisao/listar-divisao/listar-divisao.component';
import { CadastrarDivisaoComponent } from './components/divisao/cadastrar-divisao/cadastrar-divisao.component';
import { FormularioBaseDivisaoComponent } from './shared/divisao/formulario-base-divisao/formulario-base-divisao.component';
import { AtualizarDivisaoComponent } from './components/divisao/atualizar-divisao/atualizar-divisao.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    SidebarComponent,
    FooterComponent,
    PessoaComponent,
    HomeComponent,
    ListarPessoaComponent,
    PessoaSingleComponent,
    CadastrarPessoaComponent,
    FormularioBaseComponent,
    AtualizarPessoaComponent,
    PaginatorComponent,
    CadastrarTreinoComponent,
    ListarTreinoComponent,
    FormularioBaseTreinoComponent,
    AtualizarTreinoComponent,
    BuscadorComponent,
    MensagemErroComponent,
    ListarDivisaoComponent,
    CadastrarDivisaoComponent,
    FormularioBaseDivisaoComponent,
    AtualizarDivisaoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
