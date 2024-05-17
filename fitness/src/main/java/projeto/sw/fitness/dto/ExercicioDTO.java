package projeto.sw.fitness.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import projeto.sw.fitness.model.Divisao;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ExercicioDTO {

    private int idExercicio;

    private Divisao divisao;

    private String nome;

    private String comentario;

    private String series;

    private String repeticoes;

    private int intervalo;
}
