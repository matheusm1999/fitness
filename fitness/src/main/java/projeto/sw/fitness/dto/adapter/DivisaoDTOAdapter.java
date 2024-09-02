package projeto.sw.fitness.dto.adapter;

import projeto.sw.fitness.dto.DivisaoDTO;
import projeto.sw.fitness.model.Divisao;

public class DivisaoDTOAdapter implements Adapter<Divisao,DivisaoDTO>{

    @Override
    public Divisao adapt(DivisaoDTO object) {
        if(object == null){
            return null;
        }

        TreinoDTOAdapter treinoDTOAdapter = new TreinoDTOAdapter();

        Divisao divisao = new Divisao();
        divisao.setIdDivisao(object.getIdDivisao());
        divisao.setDiaSemana(object.getDiaSemana());
        divisao.setNome(object.getNome());
        divisao.setTreino(treinoDTOAdapter.adapt(object.getTreino()));

        return divisao;
    }

}
