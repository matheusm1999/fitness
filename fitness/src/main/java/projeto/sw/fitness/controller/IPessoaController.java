package projeto.sw.fitness.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import projeto.sw.fitness.dto.PessoaDTO;
import projeto.sw.fitness.dto.filter.PessoaFilter;

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


@RestController
@RequestMapping("pessoa")
public interface IPessoaController {

    @PostMapping
    @Transactional
    public ResponseEntity inserir(@RequestBody @Valid PessoaDTO pessoa);

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid PessoaDTO pessoa);

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable int id);

    @GetMapping("/paginacao")
    public ResponseEntity<Page<PessoaDTO>> listarPaginacao(@PageableDefault(size = 10, page = 0) Pageable paginacao, PessoaDTO pessoa);

    @GetMapping
    public ResponseEntity<List<PessoaDTO>> listar(PessoaDTO pessoa);

    @GetMapping("/{id}")
    public ResponseEntity<PessoaDTO> detalhar(@PathVariable int id);

}
