package projeto.sw.fitness.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Pessoa> listar(){
        return pessoaRepository.findAll();
    }

    public Pessoa get(int id){
        return pessoaRepository.getReferenceById(id);
    }

    public void excluir(int id){
        pessoaRepository.deleteById(id);
    }

    public boolean validarPessoaAtualizar(Optional<Pessoa> pessoaAntiga, Pessoa pessoa) {
        System.out.println("Fui chamado de verdade");
        if(!pessoaAntiga.isPresent()){
            throw new ValidacaoException("Pessoa não existe");
        }
        return true;
    }

}
