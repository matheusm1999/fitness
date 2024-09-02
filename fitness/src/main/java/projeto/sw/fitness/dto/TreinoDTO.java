package projeto.sw.fitness.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

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

    private Integer idTreino;

    //@Valid
    private PessoaDTO pessoa;

    private String nome;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataInicio;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataFim;

}
