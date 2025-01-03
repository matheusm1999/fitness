const base: string = 'http://localhost:8080';
const pessoa: string = base + '/pessoa';
const treino: string = base + '/treino';
const divisao: string = base + '/divisao';

export const environment = {
    apiUrl: base,
    apiURlPessoa: pessoa,
    apiURLTreino: treino,
    apiURLDivisao: divisao
}

export enum DiasSemana {
    SEGUNDA = 0,
    TERCA = 1,
    QUARTA = 2,
    QUINTA = 3,
    SEXTA = 4,
    SABADO = 5,
    DOMINGO = 6
}