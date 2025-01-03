import { Treino } from "./Treino";

export interface Divisao {
    idDivisao: number;
    nome: string;
    diaSemana: string;
    treino: Treino
}