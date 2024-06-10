package projeto.sw.fitness.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import projeto.sw.fitness.infra.exception.ValidacaoException;
import projeto.sw.fitness.model.PermissaoEnum;
import projeto.sw.fitness.model.Pessoa;
import projeto.sw.fitness.repository.PessoaRepository;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa inserir(Pessoa pessoa){
        //Validar
        pessoa.setPermissao(PermissaoEnum.ALUNO);
        return pessoaRepository.save(pessoa);
    }

    public Pessoa atualizar(Pessoa pessoa) {
        //Pessoa pessoaAntiga = pessoaRepository.getReferenceById(pessoa.getIdPessoa());
        Optional<Pessoa> pessoaAntigaOptional = pessoaRepository.findById(pessoa.getIdPessoa());

        if(!validarPessoaAtualizar(pessoaAntigaOptional, pessoa)){
            return null;
        }

        Pessoa pessoaAntiga = pessoaAntigaOptional.get();
        pessoa.setPermissao(pessoaAntiga.getPermissao());

        pessoaRepository.save(pessoa);
        return pessoa;
    }

    public Page<Pessoa> listar(Pessoa pessoa, Pageable paginacao){
        Example<Pessoa> example = pessoaBusca(pessoa);
        return pessoaRepository.findAll(example,paginacao);
    }

    public List<Pessoa> listar(Pessoa pessoa){
        Example<Pessoa> example = pessoaBusca(pessoa);
        return pessoaRepository.findAll(example);

    }

    public Pessoa get(int id){
        return pessoaRepository.getReferenceById(id);
    }

    public void excluir(int id){
        pessoaRepository.deleteById(id);
    }

    public Example<Pessoa> pessoaBusca(Pessoa pessoa){
        ExampleMatcher matcher = ExampleMatcher.matching()
            .withMatcher("nome", match -> match.contains().ignoreCase())
            .withMatcher("idPessoa", match -> match.exact())
            //.withIgnorePaths("idPessoa").withIgnoreNullValues()
            .withIgnorePaths("login").withIgnoreNullValues()
            .withIgnorePaths("permissao").withIgnoreNullValues()
            .withIgnorePaths("senha").withIgnoreNullValues();
        
        Example<Pessoa> example = Example.of(pessoa,matcher);
        return example;
    }

    public boolean validarPessoaAtualizar(Optional<Pessoa> pessoaAntiga, Pessoa pessoa) {
        System.out.println("Fui chamado de verdade");
        if(!pessoaAntiga.isPresent()){
            throw new ValidacaoException("Pessoa não existe");
        }
        return true;
    }

}
