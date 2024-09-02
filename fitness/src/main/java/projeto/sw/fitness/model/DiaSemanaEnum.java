package projeto.sw.fitness.model;

import lombok.Getter;
import lombok.ToString;

@Getter
public enum DiaSemanaEnum {
    SEGUNDA(0, "Segunda-Feira"),
    TERCA(1, "Terça-Feira"),
    QUARTA(2, "Quarta-Feira"),
    QUINTA(3, "Quinta-Feira"),
    SEXTA(4, "Sexta-Feira"),
    SABADO(5, "Sábado"),
    DOMINGO(6, "Domingo");

    private Integer valor;
    private String meaning;

    DiaSemanaEnum(Integer valor, String meaning) {
        this.valor = valor;
        this.meaning = meaning;
    }

    @Override
    public String toString(){
        return this.meaning;
    }

}
