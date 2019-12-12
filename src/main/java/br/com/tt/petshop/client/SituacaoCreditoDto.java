package br.com.tt.petshop.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import static br.com.tt.petshop.client.SituacaoCredito.NEGATIVADO;

@JsonIgnoreProperties(ignoreUnknown = true)//To ignore extra fields, the ones that are not being used in the application
public class SituacaoCreditoDto {
    @JsonProperty("situacao")
    private SituacaoCredito situacaoCredito;
    private Integer pontos;

    public SituacaoCredito getSituacaoCredito() {
        return situacaoCredito;
    }

    public void setSituacaoCredito(SituacaoCredito situacaoCredito) {
        this.situacaoCredito = situacaoCredito;
    }

    public Integer getPontos() {
        return pontos;
    }

    public void setPontos(Integer pontos) {
        this.pontos = pontos;
    }

    public boolean isRegular(SituacaoCredito situacaoCredito){
        return situacaoCredito.equals(NEGATIVADO);
    }

}
