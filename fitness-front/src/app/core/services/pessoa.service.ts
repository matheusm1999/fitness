import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map, tap } from 'rxjs';
import { Pessoa } from '../models/Pessoa';
import { environment } from 'src/app/environment/enviroment.development';
import { Paginacao, Paginador } from '../models/Paginacao';

@Injectable({
  providedIn: 'root'
})
export class PessoaService {

  constructor(private http: HttpClient) { }


  listarPessoas(): Observable<Pessoa[]> {
    return this.http.get<Pessoa[]>(environment.apiURlPessoa);
    /*
    return this.http.get<Pessoa[]>(environment.apiURlPessoa).pipe(
      tap((retorno) => console.log("Retorno: " + retorno)),
      map((retorno) => retorno)
    );
    */
  }

  listarPessoasPaginacao(parametros: HttpParams): Observable<Paginacao> {
    return this.http.get<Paginacao>(environment.apiURlPessoa + '/paginacao', {params:parametros});
  }

  cadastrarPessoa(pessoa: Pessoa): Observable<Pessoa> {
    return this.http.post<Pessoa>(environment.apiURlPessoa,pessoa);
  }

  atualizarPessoa(pessoa: Pessoa): Observable<Pessoa> {
    return this.http.put<Pessoa>(environment.apiURlPessoa,pessoa);
  }

  getPessoa(id: number): Observable<Pessoa> {
    return this.http.get<Pessoa>(environment.apiURlPessoa + '/' + id);
  }

  excluirPessoa(id: number): Observable<Pessoa> {
    return this.http.delete<any>(environment.apiURlPessoa + '/' + id);
  }

}
