const base: string = 'http://localhost:8080';
const pessoa: string = base + '/pessoa';
const treino: string = base + '/treino';

export const environment = {
    apiUrl: base,
    apiURlPessoa: pessoa,
    apiURLTreino: treino
}