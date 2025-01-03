package projeto.sw.fitness.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import projeto.sw.fitness.dto.DivisaoDTO;

@RestController
@RequestMapping("divisao")
public interface IDivisaoController {

    @PostMapping
    @Transactional
    public ResponseEntity inserir(@RequestBody @Valid DivisaoDTO divisaoDTO);

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DivisaoDTO divisaoDTO);

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Integer id);

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Integer id);

    @GetMapping("/paginacao")
    public ResponseEntity<Page<DivisaoDTO>> listarPaginacao(@PageableDefault(size = 10, page = 0) Pageable paginacao, DivisaoDTO divisao);

    @GetMapping
    public ResponseEntity<List<DivisaoDTO>> listar(DivisaoDTO divisao);


}
