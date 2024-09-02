package projeto.sw.fitness.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import projeto.sw.fitness.infra.exception.ValidacaoException;
import projeto.sw.fitness.model.Divisao;
import projeto.sw.fitness.repository.DivisaoRepository;

@Service
public class DivisaoService {

    @Autowired
    DivisaoRepository divisaoRepository;

    @Autowired
    TreinoService treinoService;

    public Divisao inserir(Divisao divisao){
        if(!validarDivisao(Optional.of(divisao))){
            return null;
        }

        return divisaoRepository.save(divisao);
    }

    public Divisao atualizar(Divisao divisao) {
        Optional<Divisao> divisaoAntiga = divisaoRepository.findById(divisao.getIdDivisao());
        
        if(!divisaoAntiga.isPresent()){
            throw new ValidacaoException("Divisão não encontrada");
        }
        
        if(!validarDivisao(Optional.of(divisao))){
            return null;
        }

        return divisaoRepository.save(divisao);
    }

    public void remover(Divisao divisao) {
        divisaoRepository.delete(divisao);
    }

    public Divisao get(Integer id){
        return divisaoRepository.getReferenceById(id);
    }

    public Page<Divisao> listar(Divisao divisao, Pageable paginanacao){
        Example<Divisao> example = divisaoBusca(divisao);
        return divisaoRepository.findAll(example, paginanacao);
    }

    public List<Divisao> listar(Divisao divisao){
        Example<Divisao> example = divisaoBusca(divisao);
        return divisaoRepository.findAll(example);
    }

    public Example<Divisao> divisaoBusca(Divisao divisao){
        ExampleMatcher matcher = ExampleMatcher.matching()
            .withMatcher("idDivisao", match -> match.exact())
            .withMatcher("treino.idTreino", match -> match.exact())
            .withIgnorePaths("nome").withIgnoreNullValues()
            .withIgnorePaths("diaSemana").withIgnoreNullValues();

        Example<Divisao> divisaoExample = Example.of(divisao,matcher);
        return divisaoExample;
    }

    public boolean validarDivisao(Optional<Divisao> divisaoOptional){
        if(!divisaoOptional.isPresent()){
            throw new ValidacaoException("Divisão não encontrada");
        }

        if(divisaoOptional.get().getTreino() == null || treinoService.get(divisaoOptional.get().getTreino().getIdTreino()) == null){
            throw new ValidacaoException("Treino não encontrado");
        }

        return true;
    }

}
