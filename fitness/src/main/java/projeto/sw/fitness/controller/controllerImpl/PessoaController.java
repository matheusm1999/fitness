package projeto.sw.fitness.controller.controllerImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import projeto.sw.fitness.controller.IPessoaController;
import projeto.sw.fitness.dto.PessoaDTO;
import projeto.sw.fitness.dto.adapter.PessoaAdapter;
import projeto.sw.fitness.dto.adapter.PessoaDTOAdapter;
import projeto.sw.fitness.model.Pessoa;
import projeto.sw.fitness.service.PessoaService;

@RestController
@RequestMapping("pessoa")
public class PessoaController implements IPessoaController{

    @Autowired
    PessoaService pessoaService;

    @Override
    public ResponseEntity inserir(@Valid PessoaDTO pessoaDTO) {
        Pessoa pessoa = pessoaService.inserir(new PessoaDTOAdapter().adapt(pessoaDTO));
        
        return ResponseEntity.status(HttpStatus.CREATED).body(new PessoaAdapter().adapt(pessoa));
    }

    @Override
    public ResponseEntity atualizar(@Valid PessoaDTO pessoaDTO) {
        Pessoa pessoa = pessoaService.atualizar(new PessoaDTOAdapter().adapt(pessoaDTO));
        return ResponseEntity.status(HttpStatus.OK).body(new PessoaAdapter().adapt(pessoa));

    }

    @Override
    public ResponseEntity excluir(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'excluir'");
    }

    @Override
    public ResponseEntity<List<PessoaDTO>> listar() {
        List<PessoaDTO> pessoas = pessoaService.listar().stream().map(new PessoaAdapter()::adapt).toList();
        return ResponseEntity.ok(pessoas);
        
    }

    @Override
    public ResponseEntity<PessoaDTO> detalhar(int id) {
        return ResponseEntity.ok(new PessoaAdapter().adapt(pessoaService.get(id)));
    }

}
