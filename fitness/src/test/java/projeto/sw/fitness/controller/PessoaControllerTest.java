package projeto.sw.fitness.controller;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import jakarta.persistence.EntityNotFoundException;
import projeto.sw.fitness.FitnessApplication;
import projeto.sw.fitness.controller.controllerImpl.PessoaController;
import projeto.sw.fitness.dto.PessoaDTO;
import projeto.sw.fitness.dto.adapter.PessoaAdapter;
import projeto.sw.fitness.dto.adapter.PessoaDTOAdapter;
import projeto.sw.fitness.infra.exception.TratadorErros;
import projeto.sw.fitness.infra.exception.ValidacaoException;
import projeto.sw.fitness.model.PermissaoEnum;
import projeto.sw.fitness.model.Pessoa;
import projeto.sw.fitness.service.PessoaService;

@SpringBootTest(classes = PessoaControllerTest.class)
@Import(value={PessoaController.class,TratadorErros.class})
@AutoConfigureMockMvc // Para conseguir injetar o MockMvc
@AutoConfigureJsonTesters // Para conseguir injetar o JacksonTester
@EnableWebMvc //Caso ao contrário ocorre um erro ao construir a imagem desta aplicação no docker
public class PessoaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<PessoaDTO> pessoaJson;

    // Caso o contrário, irá realizar operações no banco de dados quando eu fazer o
    // post, mas quero que
    // seja um objeto simulado
    @MockBean
    private PessoaService pessoaService;

    @Test
    @DisplayName("Deveria retornar status 400 quando dados inválidos")
    void cadastrarPessoaDadosInvalidos() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/pessoa"))
                .andReturn().getResponse();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria retornar status 201 quando dados válidos")
    void cadastrarPessoaDadosValidos() throws Exception {
        PessoaDTO pessoaDTO = new PessoaDTO(0, "Matheus", "matheus@email.com", "123", PermissaoEnum.ALUNO);
        Pessoa pessoaRetorno = new PessoaDTOAdapter().adapt(pessoaDTO);
        pessoaRetorno.setIdPessoa(1);

        // como pessoaService está mockado, preciso especificar o que o método inserir
        // vai retornar. Caso o contrário, será retornado Null
        // O método deve inserir a pessoa no BD e retornar o objeto com o ID preenchido
        when(pessoaService.inserir(new PessoaDTOAdapter().adapt(pessoaDTO))).thenReturn(pessoaRetorno);

        // Simula uma requisição post com os dados da pessoa em Json
        MockHttpServletResponse response = mockMvc.perform(
                MockMvcRequestBuilders.post("/pessoa")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(pessoaJson.write(pessoaDTO).getJson()))
                .andReturn().getResponse();

        // Pessoa com dados válidos deve retornar CREATEAD (201)
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        // Verifica se o ID foi gerado após a inserção
        pessoaDTO.setIdPessoa(1);
        String jsonEsperado = pessoaJson.write(pessoaDTO).getJson();
        Assertions.assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

    @Test
    @DisplayName("Deveria retornar 400 quando dados inválidos")
    void atualizarPessoaDadosInvalidos() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.put("/pessoa"))
                .andReturn().getResponse();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria retornar 409 quando erro de validação")
    void atualizarPessoaDadosErroValidacao() throws Exception {
        PessoaDTO pessoaDTO = new PessoaDTO(0, "Matheus", "matheus@email.com", "123", PermissaoEnum.ALUNO);

        // Quando um erro de validação for lançado
        when(pessoaService.atualizar(new PessoaDTOAdapter().adapt(pessoaDTO))).thenThrow(ValidacaoException.class);

        MockHttpServletResponse response = mockMvc.perform(
                MockMvcRequestBuilders.put("/pessoa")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(pessoaJson.write(pessoaDTO).getJson()))
                .andExpect(status().is4xxClientError())
                .andReturn().getResponse();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.CONFLICT.value());
    }

    @Test
    @DisplayName("Deveria retornar 200 quando dados válidos")
    void atualizarPessoaDadosValidos() throws Exception {
        PessoaDTO pessoaDTO = new PessoaDTO(1, "Matheus", "matheus@email.com", "123", PermissaoEnum.ALUNO);
        Pessoa pessoaRetorno = new PessoaDTOAdapter().adapt(pessoaDTO);

        when(pessoaService.atualizar(new PessoaDTOAdapter().adapt(pessoaDTO))).thenReturn(pessoaRetorno);

        MockHttpServletResponse response = mockMvc.perform(
                MockMvcRequestBuilders.put("/pessoa")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(pessoaJson.write(pessoaDTO).getJson()))
                .andReturn().getResponse();


        String jsonEsperado = pessoaJson.write(pessoaDTO).getJson();
        Assertions.assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }


    @Test
    @DisplayName("Deveria retornar 200 quando consultar pessoa pelo id") 
    void consultarPessoaDadosValidos() throws Exception {
        Pessoa pessoaRetorno = new Pessoa(1,"matheus","matheus@email.com","123",PermissaoEnum.ALUNO);
        
        when(pessoaService.get(1)).thenReturn(pessoaRetorno);

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/pessoa/1"))
                .andReturn().getResponse();

        String jsonEsperado = pessoaJson.write(new PessoaAdapter().adapt(pessoaRetorno)).getJson();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        Assertions.assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);

    }

}
