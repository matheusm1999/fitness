package projeto.sw.fitness.dto.adapter;

import projeto.sw.fitness.dto.PessoaDTO;
import projeto.sw.fitness.model.Pessoa;

public class PessoaAdapter implements Adapter<PessoaDTO,Pessoa>{

    @Override
    public PessoaDTO adapt(Pessoa object) {
        
        if(object == null){
            return null;
        }

        PessoaDTO pessoaDTO = new PessoaDTO();

        pessoaDTO.setIdPessoa(object.getIdPessoa());
        pessoaDTO.setLogin(object.getLogin());
        pessoaDTO.setSenha(object.getSenha());
        pessoaDTO.setNome(object.getNome());
        pessoaDTO.setPermissao(object.getPermissao());

        return pessoaDTO;

    }

}
