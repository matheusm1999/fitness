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

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import projeto.sw.fitness.dto.TreinoDTO;

public interface ITreinoController {

    @PostMapping
    @Transactional
    public ResponseEntity inserir(@RequestBody @Valid TreinoDTO treinoDTO);

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid TreinoDTO treinoDTO);

    @DeleteMapping
    public ResponseEntity remover(@RequestBody TreinoDTO treinoDTO);

    @GetMapping
    public ResponseEntity<List<TreinoDTO>> listar(TreinoDTO treinoDTO);

    @GetMapping("/paginacao")
    public ResponseEntity<Page<TreinoDTO>> listarPaginacao(@PageableDefault(size=10, page = 0) Pageable paginacao, TreinoDTO treinoDTO);

    @GetMapping("{id}")
    public ResponseEntity<TreinoDTO> detalhar(@PathVariable int id);

}
