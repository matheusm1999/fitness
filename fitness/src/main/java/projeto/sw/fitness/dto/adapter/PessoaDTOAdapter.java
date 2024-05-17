package projeto.sw.fitness.dto.adapter;

import projeto.sw.fitness.dto.PessoaDTO;
import projeto.sw.fitness.model.PermissaoEnum;
import projeto.sw.fitness.model.Pessoa;

public class PessoaDTOAdapter implements Adapter<Pessoa, PessoaDTO>{

    @Override
    public Pessoa adapt(PessoaDTO object) {

        if(object == null){
            return null;
        }

        Pessoa pessoa = new Pessoa();

        pessoa.setIdPessoa(object.getIdPessoa());
        pessoa.setLogin(object.getLogin());
        pessoa.setNome(object.getNome());
        pessoa.setSenha(object.getSenha());
        pessoa.setPermissao(object.getPermissao());

        return pessoa;
    }



}
