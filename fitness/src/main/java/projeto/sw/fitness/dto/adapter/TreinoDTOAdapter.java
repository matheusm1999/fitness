package projeto.sw.fitness.dto.adapter;

import projeto.sw.fitness.dto.TreinoDTO;
import projeto.sw.fitness.model.Treino;

public class TreinoDTOAdapter implements Adapter<Treino,TreinoDTO>{

    @Override
    public Treino adapt(TreinoDTO object) {
        if(object == null){
            return null;
        }

        PessoaDTOAdapter pessoaDTOAdapter = new PessoaDTOAdapter();

        Treino treino = new Treino();

        treino.setIdTreino(object.getIdTreino());
        treino.setDataInicio(object.getDataInicio());
        treino.setDataFim(object.getDataFim());
        treino.setNome(object.getNome());
        treino.setPessoa(pessoaDTOAdapter.adapt(object.getPessoa()));

        return treino;
    }



}
