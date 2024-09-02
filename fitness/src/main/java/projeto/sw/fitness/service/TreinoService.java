package projeto.sw.fitness.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import projeto.sw.fitness.infra.exception.ValidacaoException;
import projeto.sw.fitness.model.Pessoa;
import projeto.sw.fitness.model.Treino;
import projeto.sw.fitness.repository.TreinoRepository;

@Service
public class TreinoService {

    @Autowired
    private TreinoRepository treinoRepository;

    @Autowired
    private PessoaService pessoaService;

    public Treino inserir(Treino treino) {

        Optional<Treino> treinoOptional = Optional.of(treino);
        if(!validarTreino(treinoOptional)){
            return null;
        }

        return treinoRepository.save(treino);
    }

    public Treino atualizar(Treino treino) {
        Optional<Treino> treinoAntigoOptional = treinoRepository.findById(treino.getIdTreino());

        if(!validarTreino(treinoAntigoOptional)) {
            return null;
        }

        treinoRepository.save(treino);
        return treino;
    }

    public Treino get (int id) {
        return treinoRepository.getReferenceById(id);
    }

    public List<Treino> listar(Treino treino) {
        Example<Treino> exemaple = treinoBusca(treino);
        return treinoRepository.findAll(exemaple);
    }

    public Page<Treino> listar(Treino treino, Pageable paginacao) {
        Example<Treino> exemaple = treinoBusca(treino);
        return treinoRepository.findAll(exemaple, paginacao);
    }

    public void remover(Treino treino) {
        treinoRepository.delete(treino);
    }

    public Example<Treino> treinoBusca(Treino treino) {
        System.out.println("Valor do treino: ");
        System.out.println(treino);

        ExampleMatcher matcher = ExampleMatcher.matching()
            .withMatcher("idTreino", match -> match.exact())
            .withMatcher("pessoa.idPessoa", match -> match.exact())
            .withIgnorePaths("nome").withIgnoreNullValues()
            .withIgnorePaths("dataInicio").withIgnoreNullValues()
            .withIgnorePaths("dataFim").withIgnoreNullValues();

        Example<Treino> treinoExample = Example.of(treino,matcher);
        return treinoExample;
    }

    public boolean validarTreino(Optional<Treino> treinoAntigo) {

        if(!treinoAntigo.isPresent()) {
            throw new ValidacaoException("Treino não encontrado!");
        }

        if(treinoAntigo.get().getPessoa() == null || pessoaService.get(treinoAntigo.get().getPessoa().getIdPessoa()) == null) {
            throw new ValidacaoException("Pessoa não encontrada");
        }

        return true;
    }
    
}
