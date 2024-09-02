package projeto.sw.fitness.controller;

import static org.mockito.Mockito.when;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

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

import projeto.sw.fitness.controller.controllerImpl.TreinoController;
import projeto.sw.fitness.dto.PessoaDTO;
import projeto.sw.fitness.dto.TreinoDTO;
import projeto.sw.fitness.dto.adapter.TreinoAdapter;
import projeto.sw.fitness.dto.adapter.TreinoDTOAdapter;
import projeto.sw.fitness.infra.exception.TratadorErros;
import projeto.sw.fitness.infra.exception.ValidacaoException;
import projeto.sw.fitness.model.Pessoa;
import projeto.sw.fitness.model.Treino;
import projeto.sw.fitness.service.TreinoService;

@SpringBootTest(classes = TreinoControllerTest.class)
@Import(value={TreinoController.class,TratadorErros.class})
@AutoConfigureMockMvc // Para conseguir injetar o MockMvc
@AutoConfigureJsonTesters // Para conseguir injetar o JacksonTester
@EnableWebMvc //Caso ao contrário ocorre um erro ao construir a imagem desta aplicação no docker
public class TreinoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<TreinoDTO> treinoJson;

    @MockBean
    private TreinoService treinoService;

    @Test
    @DisplayName("Deveria retornar 200 quando consultar treino pelo id")
    void consulTreinoDadosValidos() throws Exception {
        Treino treinoRetorno = new Treino(1, new Pessoa(1), "Matheus", LocalDate.now(), LocalDate.now(), null);

        when(treinoService.get(1)).thenReturn(treinoRetorno);

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/treino/1")
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("UTF-8"))
        .andReturn().getResponse();

        String jsonEsperado = treinoJson.write(new TreinoAdapter().adapt(treinoRetorno)).getJson();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        Assertions.assertThat(response.getContentAsString(StandardCharsets.UTF_8).toString()).isEqualTo(jsonEsperado);
    }

    @Test
    @DisplayName("Deveria retornar 201 quando cadastrar treino com dados válidos")
    void cadastrarTreinoDadosValidos() throws IOException, Exception{
        TreinoDTO treinoDTO = new TreinoDTO(0, new PessoaDTO(1), "Matheus", LocalDate.now(), LocalDate.now());
        Treino treinoRetorno = new TreinoDTOAdapter().adapt(treinoDTO);
        treinoRetorno.setIdTreino(1);

        //Como treinoService está mockado, preciso especificar o que o método "inserir" deve retornar
        when(treinoService.inserir(new TreinoDTOAdapter().adapt(treinoDTO))).thenReturn(treinoRetorno);

        //Realiza um post para cadastar um treino
        MockHttpServletResponse response = mockMvc.perform(
                MockMvcRequestBuilders.post("/treino")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(treinoJson.write(treinoDTO).getJson()))
                .andReturn().getResponse();

        //Como os dados estão corretos, deve retornar código 201
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        //Verifica se o ID foi gerado (registro inserido no banco de dados)
        treinoDTO.setIdTreino(1);
        String jsonEsperado = treinoJson.write(treinoDTO).getJson();
        Assertions.assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }   

    @Test
    @DisplayName("Deveria retornar status 400 quando dados inválidos")
    void cadastrarTreinoDadosInvalidos() throws Exception{
       MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/treino"))
            .andReturn().getResponse();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria retornar status 200 quando dados válidos")
    void atualizarTreinoDadosValidos() throws IOException, Exception {
        TreinoDTO treinoDTO = new TreinoDTO(1, new PessoaDTO(1), "Matheus", LocalDate.now(), LocalDate.now());
        Treino treinoRetorno = new TreinoDTOAdapter().adapt(treinoDTO);

        when(treinoService.atualizar(new TreinoDTOAdapter().adapt(treinoDTO))).thenReturn(treinoRetorno);

        MockHttpServletResponse response = mockMvc.perform(
            MockMvcRequestBuilders.put("/treino")
            .contentType(MediaType.APPLICATION_JSON)
            .content(treinoJson.write(treinoDTO).getJson()))
        .andReturn().getResponse();

        String jsonEsperado = treinoJson.write(treinoDTO).getJson();
        Assertions.assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Deveria retornar erro 400 quando dados inválidos")
    void atualizarTreinoDadosInvalidos() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(
            MockMvcRequestBuilders.put("/treino")
        ).andReturn().getResponse();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());

    }

    @Test
    @DisplayName("Deveria retornar status 409 quando dados inválidos")
    void atualizarTreinoErroValidacao() throws IOException, Exception {
        TreinoDTO treinoDTO = new TreinoDTO(0, new PessoaDTO(1), "Matheus", LocalDate.now(), LocalDate.now());

        when(treinoService.atualizar(new TreinoDTOAdapter().adapt(treinoDTO))).thenThrow(ValidacaoException.class);

        MockHttpServletResponse response = mockMvc.perform(
            MockMvcRequestBuilders.put("/treino")
            .contentType(MediaType.APPLICATION_JSON)
            .content(treinoJson.write(treinoDTO).getJson()))
        .andReturn().getResponse();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.CONFLICT.value());
    }
    
}
