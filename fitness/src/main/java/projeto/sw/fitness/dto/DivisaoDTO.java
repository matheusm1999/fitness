package projeto.sw.fitness.dto;

import java.time.DayOfWeek;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import projeto.sw.fitness.model.DiaSemanaEnum;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DivisaoDTO {

    private Integer idDivisao;

    private TreinoDTO treino;

    private String nome;

    private DiaSemanaEnum diaSemana;
    
    private List<ExercicioDTO> exercicios;

}
