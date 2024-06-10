package projeto.sw.fitness.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder.Default;
import projeto.sw.fitness.model.PermissaoEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PessoaDTO {

    private Integer idPessoa;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @Email(message = "Formato de email inválido")
    @NotBlank(message = "Login é obrigatório")
    private String login;

    @NotBlank(message = "Senha é obrigatória")
    private String senha;

    private PermissaoEnum permissao;

}
