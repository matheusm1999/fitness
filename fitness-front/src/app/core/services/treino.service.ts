import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Treino } from '../models/Treino';
import { environment } from 'src/app/environment/enviroment.development';
import { Paginacao } from '../models/Paginacao';

@Injectable({
  providedIn: 'root'
})
export class TreinoService {

  constructor(private http: HttpClient) { }

  getTreino(id:number): Observable<Treino> {
    return this.http.get<Treino>(environment.apiURLTreino + '/' + id);
  }

  listarTreinos(): Observable<Treino[]> {
    return this.http.get<Treino[]>(environment.apiURLTreino);
  }

  listarTreinosPaginacao(parametros: HttpParams): Observable<Paginacao> {
    return this.http.get<Paginacao>(environment.apiURLTreino + '/paginacao', {params:parametros});
  }

  cadastrarTreino(treino: Treino): Observable<Treino> {
    return this.http.post<Treino>(environment.apiURLTreino, treino);
  }

  atualizarTreino(treino: Treino): Observable<Treino> {
    return this.http.put<Treino>(environment.apiURLTreino, treino);
  }

}
