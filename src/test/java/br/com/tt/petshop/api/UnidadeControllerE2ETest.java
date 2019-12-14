package br.com.tt.petshop.api;


import br.com.tt.petshop.config.ModelMapperConfig;
import br.com.tt.petshop.service.ClienteService;
import br.com.tt.petshop.service.UnidadeService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UnidadeControllerE2ETest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void deveriaRetornarListaVazia() throws Exception{
        mockMvc
                .perform(MockMvcRequestBuilders.get("/unidades"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    @Sql("/sql/delete_all_test_data.sql")
    @Sql("/sql/insert_test_data.sql")
    public void deveriaRetornarLista() throws Exception{
        mockMvc
                .perform(MockMvcRequestBuilders.get("/unidades"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nome",
                        CoreMatchers.is("Pet Shop do Sven")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].endereco",
                        CoreMatchers.is("Rua da Cachorrada Braba, 500")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].telefone",
                        CoreMatchers.is("32165498")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].cnpj",
                        CoreMatchers.is("0111445551000")));
    }
}
