/*
export class Pessoa {
    idPessoa: number;
    nome: string;
    login: string;
    senha: string;
    permissao: string;


    constructor(pessoa) {
        this.idPessoa = pessoa.idPessoa;
        this.nome = pessoa.nome;
        this.login = pessoa.login;
        this.senha = pessoa.senha;
        this.permissao = pessoa.permissao;
    }
}
*/

export interface Pessoa {
    idPessoa: number;
    nome: string;
    login: string;
    senha: string;
    permissao: string;
}



