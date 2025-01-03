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

import projeto.sw.fitness.controller.controllerImpl.DivisaoController;
import projeto.sw.fitness.controller.controllerImpl.PessoaController;
import projeto.sw.fitness.infra.exception.TratadorErros;

@SpringBootTest(classes = DivisaoControllerTest.class)
@Import(value={DivisaoController.class,TratadorErros.class})
@AutoConfigureMockMvc // Para conseguir injetar o MockMvc
@AutoConfigureJsonTesters // Para conseguir injetar o JacksonTester
@EnableWebMvc //Caso ao contrário ocorre um erro ao construir a imagem desta aplicação no docker
public class DivisaoControllerTest {

    /* 

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<DivisaoControllerDTO> divisaoJson;

    @MockBean
    private DivisaoService divisaoService;
*/

}
