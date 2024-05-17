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
    CadastrarPessoaComponent
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
