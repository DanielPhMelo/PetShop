package br.com.tt.petshop.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SituacaoCreditoClient {

    private final RestTemplate restTemplate;
    private final String apisCreditoUrl;

    public SituacaoCreditoClient(RestTemplate restTemplate,
                                 @Value("$apis.credito.url")String apisCreditoUrl){
        this.restTemplate = restTemplate;
        this.apisCreditoUrl = apisCreditoUrl;
    }

    public SituacaoCreditoDto consultaSituacao(String cpf){
        ResponseEntity<SituacaoCreditoDto> response = restTemplate.
                getForEntity(apisCreditoUrl,
                        SituacaoCreditoDto.class, cpf);
        if (response.getStatusCode().equals(HttpStatus.OK)){
            return response.getBody();
        }
        throw new IllegalArgumentException("Erro ao acessar serviço de crédito");
    }
}
