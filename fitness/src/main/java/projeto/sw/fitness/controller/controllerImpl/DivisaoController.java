package projeto.sw.fitness.controller.controllerImpl;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import projeto.sw.fitness.controller.IDivisaoController;
import projeto.sw.fitness.dto.DivisaoDTO;
import projeto.sw.fitness.dto.adapter.DivisaoAdapter;
import projeto.sw.fitness.dto.adapter.DivisaoDTOAdapter;
import projeto.sw.fitness.model.Divisao;
import projeto.sw.fitness.service.DivisaoService;

@RestController
@RequestMapping("divisao")
public class DivisaoController implements IDivisaoController{

    @Autowired
    DivisaoService divisaoService;

    @Override
    public ResponseEntity inserir(@Valid DivisaoDTO divisaoDTO) {
        Divisao divisao = divisaoService.inserir(new DivisaoDTOAdapter().adapt(divisaoDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(new DivisaoAdapter().adapt(divisao));
    }

    @Override
    public ResponseEntity atualizar(@Valid DivisaoDTO divisaoDTO) {
        Divisao divisao = divisaoService.atualizar(new DivisaoDTOAdapter().adapt(divisaoDTO));
        return ResponseEntity.status(HttpStatus.OK).body(new DivisaoAdapter().adapt(divisao));
    }
    
    @Override
    public ResponseEntity excluir(Integer id) {
        divisaoService.remover(new Divisao(id));
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity detalhar(Integer id) {
        Divisao divisao = divisaoService.get(id);
        return ResponseEntity.ok().body(new DivisaoAdapter().adapt(divisao));
    }

    @Override
    public ResponseEntity<Page<DivisaoDTO>> listarPaginacao(Pageable paginacao, DivisaoDTO divisao) {
        Page<DivisaoDTO> divisoes = divisaoService.listar(new DivisaoDTOAdapter().adapt(divisao), paginacao).map(new DivisaoAdapter()::adapt);
        return ResponseEntity.ok(divisoes);
    }

    @Override
    public ResponseEntity<List<DivisaoDTO>> listar(DivisaoDTO divisao) {
        List<DivisaoDTO> divisoes = divisaoService.listar(new DivisaoDTOAdapter().adapt(divisao)).stream().map(new DivisaoAdapter()::adapt).toList();
        return ResponseEntity.ok(divisoes);
    }

}