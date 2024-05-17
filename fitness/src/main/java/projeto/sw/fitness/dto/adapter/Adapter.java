package projeto.sw.fitness.dto.adapter;

/*
 * Interface Adapter. Serve para adptar um objeto para outro tipo
 * Serão adaptados DTOs para os modelos e vice-versa
 * D -> Destino (retorno do método de adaptar) e O -> Origem (parâmetro do método de adaptar)
 */
public interface Adapter<D,O> {

    public D adapt(O object);
}
