package projeto.sw.fitness.model;

import java.time.DayOfWeek;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Divisao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idDivisao")
@ToString
public class Divisao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDivisao;

    @ManyToOne()
    @JoinColumn(name = "idTreino")
    private Treino treino;

    private String nome;

    private DiaSemanaEnum diaSemana;

    @OneToMany(mappedBy = "divisao")
    private List<Exercicio> exercicios;


    public Divisao(Integer idDivisao){
        this.idDivisao = idDivisao;
    }

}
