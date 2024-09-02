package projeto.sw.fitness.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Pessoa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idPessoa")
@ToString
public class Pessoa {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPessoa;

    private String nome;

    private String login;

    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(name = "permissao", length = 30, columnDefinition = "VARCHAR(30)")
    private PermissaoEnum permissao;

    public Pessoa(Integer idPessoa){
        this.idPessoa = idPessoa;
    }
}
