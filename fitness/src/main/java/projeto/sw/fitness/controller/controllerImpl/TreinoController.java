package projeto.sw.fitness.controller.controllerImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import projeto.sw.fitness.controller.ITreinoController;
import projeto.sw.fitness.dto.PessoaDTO;
import projeto.sw.fitness.dto.TreinoDTO;
import projeto.sw.fitness.dto.adapter.PessoaAdapter;
import projeto.sw.fitness.dto.adapter.PessoaDTOAdapter;
import projeto.sw.fitness.dto.adapter.TreinoAdapter;
import projeto.sw.fitness.dto.adapter.TreinoDTOAdapter;
import projeto.sw.fitness.model.Treino;
import projeto.sw.fitness.service.TreinoService;

@RestController
@RequestMapping("treino")
public class TreinoController implements ITreinoController{

    @Autowired
    TreinoService treinoService;

    @Override
    public ResponseEntity inserir(@Valid TreinoDTO treinoDTO) {
        Treino treino = treinoService.inserir(new TreinoDTOAdapter().adapt(treinoDTO));

        return ResponseEntity.status(HttpStatus.CREATED).body(new TreinoAdapter().adapt(treino));
    }

    @Override
    public ResponseEntity atualizar(@Valid TreinoDTO treinoDTO) {
        Treino treino = new TreinoDTOAdapter().adapt(treinoDTO);
        treino = treinoService.atualizar(treino);

        return ResponseEntity.status(HttpStatus.OK).body(new TreinoAdapter().adapt(treino));
    }

    @Override
    public ResponseEntity remover(TreinoDTO treinoDTO) {
        treinoService.remover(new TreinoDTOAdapter().adapt(treinoDTO));
        return ResponseEntity.status(HttpStatus.OK).body(treinoDTO);
    }

    @Override
    public ResponseEntity<List<TreinoDTO>> listar(TreinoDTO treinoDTO) {
        List<TreinoDTO> treinos = treinoService.listar(new TreinoDTOAdapter().adapt(treinoDTO)).stream().map(new TreinoAdapter()::adapt).toList();
        return ResponseEntity.ok(treinos);
    }

    @Override
    public ResponseEntity<Page<TreinoDTO>> listarPaginacao(Pageable paginacao, TreinoDTO treinoDTO) {
        Page<TreinoDTO> treinos = treinoService.listar(new TreinoDTOAdapter().adapt(treinoDTO),paginacao).map(new TreinoAdapter()::adapt);
        return ResponseEntity.ok(treinos);
    }

    @Override
    public ResponseEntity<TreinoDTO> detalhar(int id) {
        Treino treino = treinoService.get(id);

        return ResponseEntity.ok(new TreinoAdapter().adapt(treino));
    }





    

}
