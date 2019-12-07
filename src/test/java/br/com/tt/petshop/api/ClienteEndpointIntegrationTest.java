package br.com.tt.petshop.api;

import br.com.tt.petshop.config.ModelMapperConfig;
import br.com.tt.petshop.model.Cliente;
import br.com.tt.petshop.service.ClienteService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(controllers = ClienteEndpoint.class)
@ExtendWith(SpringExtension.class)
@Import(ModelMapperConfig.class)
class ClienteEndpointIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    @Test
    public void deveriaRetornarListaClientesVazia() throws Exception{
        mockMvc
                .perform(MockMvcRequestBuilders.get("/clientes"))
                .andExpect(MockMvcResultMatchers.status()
                    .is(200))
                .andExpect(MockMvcResultMatchers.content()
                        .string("[]"))
                        .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deveriaRetornarFulano() throws Exception{
        //Arrange
        Cliente cliente = new Cliente();
        cliente.setNome("Fulano");
        cliente.setCpf("111.222.333-12");
        Mockito.doReturn(Arrays.asList(cliente))
                .when(clienteService).listar(Optional.empty(), Optional.empty());

        //Act & Assert
        mockMvc
                .perform(MockMvcRequestBuilders.get("/clientes"))
                .andExpect(MockMvcResultMatchers.status()
                        .is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nome",
                        CoreMatchers.is("Fulano")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].cpf",
                        CoreMatchers.is("111.222.333-12")))
                .andDo(MockMvcResultHandlers.print());
    }
}