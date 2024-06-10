/**
 * Retorno do endpoint de listar com paginação
 */
export interface Paginacao {
    content: object;
    pageable: Pageable;
    last: boolean; //Indica se é a última página
    totalPages: number; //Quantas páginas ainda posso avançar considerando o atual tamanho de página
    totalElements: number; //Quantos elementos existem no total
    size: number; //Tamanho da página
    numberOfElements: number; //Quantidade de elementos retornados na página
    number: number; //Página atual

}

interface Pageable {
    pageNumber: number; //Página atual (utilizado nos parâmetros)
    pageSize: number; //Tamanho da página atual (utilizado nos parâmetros)
}

/**
 * Utilizado para troca de informação entre o componente de listar e o de paginação
 */
export class Paginador {
    tamanhoPagina: number;
    pagina: number;

    constructor() {
        this.tamanhoPagina = 10;
        this.pagina = 0;
    }
}