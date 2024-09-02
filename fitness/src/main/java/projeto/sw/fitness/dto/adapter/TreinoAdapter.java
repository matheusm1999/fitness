package projeto.sw.fitness.dto.adapter;

import projeto.sw.fitness.dto.TreinoDTO;
import projeto.sw.fitness.model.Treino;

public class TreinoAdapter implements Adapter <TreinoDTO,Treino>{

    @Override
    public TreinoDTO adapt(Treino object) {
        if(object == null){
            return null;
        }

        PessoaAdapter pessoaAdapter = new PessoaAdapter();

        TreinoDTO treinoDTO = new TreinoDTO();
        
        treinoDTO.setIdTreino(object.getIdTreino());
        treinoDTO.setDataInicio(object.getDataInicio());
        treinoDTO.setDataFim(object.getDataFim());
        treinoDTO.setNome(object.getNome());
        treinoDTO.setPessoa(pessoaAdapter.adapt(object.getPessoa()));

        return treinoDTO;
    }

}
