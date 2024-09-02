package projeto.sw.fitness.dto.adapter;

import projeto.sw.fitness.dto.DivisaoDTO;
import projeto.sw.fitness.model.Divisao;

public class DivisaoAdapter implements Adapter<DivisaoDTO,Divisao> {

    @Override
    public DivisaoDTO adapt(Divisao object) {
        if(object == null){
            return null;
        }

        TreinoAdapter treinoAdapter = new TreinoAdapter();

        DivisaoDTO divisaoDTO = new DivisaoDTO();

        divisaoDTO.setIdDivisao(object.getIdDivisao());
        divisaoDTO.setDiaSemana(object.getDiaSemana());
        divisaoDTO.setNome(object.getNome());
        divisaoDTO.setTreino(treinoAdapter.adapt(object.getTreino()));

        return divisaoDTO;
    }

}
