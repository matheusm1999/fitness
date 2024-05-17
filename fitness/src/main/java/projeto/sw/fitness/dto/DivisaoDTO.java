package projeto.sw.fitness.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DivisaoDTO {

    private int idDivisao;

    private TreinoDTO treino;

    private String nome;

    private String diaSemana;
    
    private List<ExercicioDTO> exercicios;

}
