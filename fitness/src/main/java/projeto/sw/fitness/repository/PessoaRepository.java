package projeto.sw.fitness.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import projeto.sw.fitness.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa,Integer>{

}
