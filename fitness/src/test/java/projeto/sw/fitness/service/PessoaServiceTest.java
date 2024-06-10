package projeto.sw.fitness.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import jakarta.persistence.EntityNotFoundException;
import projeto.sw.fitness.infra.exception.ValidacaoException;
import projeto.sw.fitness.model.PermissaoEnum;
import projeto.sw.fitness.model.Pessoa;
import projeto.sw.fitness.repository.PessoaRepository;

@ExtendWith(MockitoExtension.class) //Para o mockito conseguir interceptar essa classe e injetar os objetos
public class PessoaServiceTest {

    @Spy
    @InjectMocks
    private PessoaService pessoaService;

    @Mock
    private PessoaRepository pessoaRepository;

    @Captor
    private ArgumentCaptor<Pessoa> pessoaCaptor; //Para permitir capturar os argumentos passados para um método

    @Test
    @DisplayName("Deve atualiar a pessoa os dados informados corretamente")
    //Simula o método original: encontra a pessoa pelo id, valida e então atualiza. Ao final, verifica se a pessoa retornada (atualizada) é aquela que foi passada nos parâmetros
    void deveriaAtualizarPessoa() {
        Pessoa pessoa = new Pessoa(1,"Fulano","fulano@email.com","312",PermissaoEnum.ALUNO);
        Pessoa pessoaAntiga = new Pessoa(1,"Matheus","matheus@email.com","123",PermissaoEnum.ALUNO);

        when(pessoaRepository.findById(pessoa.getIdPessoa())).thenReturn(Optional.of(pessoaAntiga));
        //when(pessoaService.validarPessoaAtualizar(Optional.of(pessoaAntiga), pessoa)).thenReturn(true);
        doReturn(true).when(pessoaService).validarPessoaAtualizar(Optional.of(pessoaAntiga), pessoa); //Melhor utilizar o doReturn nesse caso pois assim o método validarPessoa não será chamado.
        //Se fosse utilizado when().thenReturn(), seria chamado o método validarPessoaAtualizar de fato, e quaisquer exceções que o método lançasse, teriam que ser lidadas

        Pessoa pessoaRetorno = pessoaService.atualizar(pessoa);

        //Verifico se o meu método de salvar (atualizar o registro) foi chamado - poderia ser //then(pessoaRepository).should().save(pessoa);
        //Com capture() consigo obter o argumento que foi passado ao método pessoaRepository.save() dentro do método pessoaService.atualizar(pessoa)
        //No caso, o que deveria ter sido passado como argumento ao método pessoaRepository.save() é o parâmetro pessoa
        //Nesse exemplo não precisaria do capture() pois já tenho acesso ao argumento que foi utilizado (pessoa)
        //Mas seria útil se um objeto (ou qualquer variável) fosse criado dentro do método atualizar(). Nessa situação, poderia utilizar o capture para obter essa variável
        then(pessoaRepository).should().save(pessoaCaptor.capture());
        //Pessoa pessoaAtualizada = pessoaCaptor.getValue(); Desse jeito consigo pegar o valor do objeto que foi criado internamente no método atualizar
        assertEquals(pessoa, pessoaRetorno);
    }


    @Test
    @DisplayName("Deve retornar ValidacaoException quando id não for encontrado")
    void deveriaRetornarNullAoAtualizarPessoa() {
        Pessoa pessoa = new Pessoa(0,"Fulano","fulano@email.com","312",PermissaoEnum.ALUNO);

        //Quando a pessoa não for encontrada
        when(pessoaRepository.findById(pessoa.getIdPessoa())).thenReturn(Optional.ofNullable(null));
        //doThrow foi utilizado pois desse modo o método original não é chamado de fato
        //Também posso remover essa linha do doThrow, pois como será retornado um optional null pelo findById(), o método validarPessoaAtualizar() em pessoaService.atulizar() irá chamar o método validarPessoa() com o parâmetro pessoaAntiga como null e lançará uma exceção
        //doThrow(new ValidacaoException("Pessoa não existe")).when(pessoaService).validarPessoaAtualizar(Optional.ofNullable(null), pessoa);

        //Deve retornar uma Validação Exception
        assertThrows(ValidacaoException.class, () -> pessoaService.atualizar(pessoa));
        
    }

    @Test
    @DisplayName("Deve retornar a pessoa ao consultá-la pelo ID")
    void deveriaRetornarPessoaAoConsultarPorID() {
        Pessoa pessoa = new Pessoa(1,"Fulano","fulano@email.com","312",PermissaoEnum.ALUNO);

        when(pessoaRepository.getReferenceById(pessoa.getIdPessoa())).thenReturn(pessoa);

        Pessoa pessoaRetorno = pessoaService.get(pessoa.getIdPessoa());

        assertEquals(pessoa,pessoaRetorno);

    }

    @Test
    @DisplayName("Deve retornar EntityNotFoundException quando pessoa não existe ao consultá-la pelo ID")
    void deveriaRetornar500AoConsultarPorID() {
        when(pessoaRepository.getReferenceById(0)).thenThrow(EntityNotFoundException.class);

        assertThrows(EntityNotFoundException.class, () -> pessoaService.get(0));
    }

}