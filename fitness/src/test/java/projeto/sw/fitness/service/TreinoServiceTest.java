package projeto.sw.fitness.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import javax.swing.text.html.parser.Entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import jakarta.persistence.EntityNotFoundException;
import projeto.sw.fitness.dto.TreinoDTO;
import projeto.sw.fitness.dto.adapter.TreinoAdapter;
import projeto.sw.fitness.infra.exception.ValidacaoException;
import projeto.sw.fitness.model.Pessoa;
import projeto.sw.fitness.model.Treino;
import projeto.sw.fitness.repository.TreinoRepository;

@ExtendWith(MockitoExtension.class)
public class TreinoServiceTest {
    @Spy
    @InjectMocks
    private TreinoService treinoService;

    @Mock
    private PessoaService pessoaService;

    @Mock
    private TreinoRepository treinoRepository;

    @Captor
    private ArgumentCaptor<Treino> treinoCaptor;

    @Test
    @DisplayName("Deveria inserir treino com dados informados corretamente")
    public void deveriaInserirTreinoDadoValidos(){
        Treino treino = new Treino(0, new Pessoa(1), "TREINO ABC", LocalDate.now(), LocalDate.now(), null);
        Treino treinoRetorno = new Treino(1, new Pessoa(1), "TREINO ABC", LocalDate.now(), LocalDate.now(), null);

        //Quando o objeto que está com anotação spy utiliza when().thenReturn(), estará chamando o método real.
        //doReturn evitará com que isso aconteça
        doReturn(true).when(treinoService).validarTreino(Optional.of(treino));
        when(treinoRepository.save(treino)).thenReturn(treinoRetorno);

        Treino treinoRetornoInserir = treinoService.inserir(treino);
        then(treinoRepository).should().save(treinoCaptor.capture());

        assertEquals(treinoRetorno, treinoRetornoInserir);
    }
    
    @Test
    @DisplayName("Deveria retornar validacaoException ao salvar treino")
    //Nesse caso, retorna Validação exception pois a pessoa não existe.
    //Seria interessante realizar um teste para cada validação
    public void deveriaRetornarValidacaoExceptionAoSalvarTreinoDadosInvalidos(){
        Treino treino = new Treino(0, new Pessoa(0), "Matheus", LocalDate.now(), LocalDate.now(), null);

        when(pessoaService.get(treino.getPessoa().getIdPessoa())).thenReturn(null);
        //doThrow(ValidacaoException.class).when(treinoService).validarTreino(Optional.of(treino));

        assertThrows(ValidacaoException.class, () -> treinoService.inserir(treino));

    }

    @Test
    @DisplayName("Deve atualizar o treinos com os dados informados corretamente")
    public void deveriaAtualizarTreinoDadosValidos(){
        //Simula o método original: Encontra o treino, valida os dados e então atualiza.
        Treino treino = new Treino(1, new Pessoa(1), "Matheus", LocalDate.now(), LocalDate.now(), null);
        Treino treinoAntigo = new Treino(1, new Pessoa(1), "Fulano", LocalDate.now().plusDays(1), LocalDate.now(), null);

        //Primeiro passo do método: Encontrar o treino por ID
        when(treinoRepository.findById(1)).thenReturn(Optional.of(treinoAntigo));
        //Segundo passo do método: Valida os dados
        doReturn(true).when(treinoService).validarTreino(Optional.of(treinoAntigo));

        //Agora com os mocks realizados e tratados, chamo o serviço a ser testado
        Treino treinoRetorno = treinoService.atualizar(treino);
        then(treinoRepository).should().save(treinoCaptor.capture()); //treinoCaptor.capture() retorna o treino que foi passado como parâmetro na linha acima

        assertEquals(treino, treinoRetorno);
    }

    @Test
    @DisplayName("Deveria retornar uma ValidacaoException ao atualizar o treino")
    public void deveriaRetornarNullAoAtualizarTreinoComTreinoInvalido(){
        Treino treino = new Treino(0, new Pessoa(1), "TREINO ABC", LocalDate.now(), LocalDate.now(), null);

        //Considera um treino que não foi encontrado
        when(treinoRepository.findById(0)).thenReturn(Optional.ofNullable(null));

        assertThrows(ValidacaoException.class, () -> treinoService.atualizar(treino));
    }

    @Test
    @DisplayName("Deveria retornar uma ValidacaoExcepetion ao atualizar o treino com pessoa inválida")
    public void deveriaRetornarNullAoAtualizarTreinoComPessoaInvalida(){
        Treino treino = new Treino(1, new Pessoa(0), "TREINO ABC", LocalDate.now(), LocalDate.now(), null);
        
        //Retorna o treino encontrado (chamado no método de atualizar)
        when(treinoRepository.findById(treino.getIdTreino())).thenReturn(Optional.of(treino));
        //Pessoa do treino é inválida (chamado no método de validar)
        when(pessoaService.get(treino.getPessoa().getIdPessoa())).thenReturn(null);

        assertThrows(ValidacaoException.class,() -> treinoService.atualizar(treino));
    }

    @Test
    @DisplayName("Deve retornor o treino ao consultá-lo por ID")
    void deveriaRetornarTreinoAoConsultarPorID() {
        Treino treino = new Treino(1, new Pessoa(0), "TREINO ABC", LocalDate.now(), LocalDate.now(), null);

        when(treinoRepository.getReferenceById(treino.getIdTreino())).thenReturn(treino);

        Treino treinoRetorno = treinoService.get(treino.getIdTreino());

        assertEquals(treino, treinoRetorno);

    }

    @Test
    @DisplayName("Deveria retornar EntityNotFoundException quando treino não existe ao consultá-lo por ID")
    void deveriaRetornar500AoConsultarPorID() {
        when(treinoService.get(0)).thenThrow(EntityNotFoundException.class);

        assertThrows(EntityNotFoundException.class, () -> treinoService.get(0));
    }

}
