package projeto.sw.fitness.dto;

import java.time.LocalDate;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TreinoDTO {

    private int idTreino;

    @Valid
    private PessoaDTO pessoa;

    private String nome;

    private LocalDate dataInicio;
    
    private LocalDate dataFim;

}
