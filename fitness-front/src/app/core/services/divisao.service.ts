import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/app/environment/enviroment.development';
import { Divisao } from '../models/Divisao';
import { Paginacao } from '../models/Paginacao';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DivisaoService {

  constructor(private http: HttpClient) { }

  getDivisao(id: number): Observable<Divisao>{
    return this.http.get<Divisao>(environment.apiURLDivisao + '/' + id);
  }

  listarDivisoes(parametros: HttpParams): Observable<Divisao[]> {
    return this.http.get<Divisao[]>(environment.apiURLDivisao, {params:parametros});
  }

  listarDivisoesPaginacao(parametros: HttpParams): Observable<Paginacao> {
    return this.http.get<Paginacao>(environment.apiURLDivisao + '/paginacao', {params:parametros});
  }

  cadastrarDivisao(divisao: Divisao): Observable<Divisao> {
    return this.http.post<Divisao>(environment.apiURLDivisao, divisao);
  }

  atualizarDivisao(divisao: Divisao): Observable<Divisao> {
    return this.http.put<Divisao>(environment.apiURLDivisao, divisao);
  }

  excluirDivisao(id: number): Observable<Divisao> {
    return this.http.delete<Divisao>(environment.apiURLDivisao + '/' + id);
  }

}
