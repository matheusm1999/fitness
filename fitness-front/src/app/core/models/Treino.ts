import { Pessoa } from "./Pessoa";

export interface Treino{
    idTreino: number;
    pessoa: Pessoa;
    nome: string;
    dataInicio: Date;
    dataFim: Date;
}