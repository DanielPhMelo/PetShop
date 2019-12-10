package br.com.tt.petshop.dto;

public class RelatorioClienteDto {
    private String nome;
    private String cpf;
    private Long unidadeId;
    private String unidadeNome;

    public RelatorioClienteDto(String nome, String cpf, Long unidadeId, String unidadeNome){
        this.nome = nome;
        this.cpf = cpf;
        this.unidadeId = unidadeId;
        this.unidadeNome = unidadeNome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Long getUnidadeId() {
        return unidadeId;
    }

    public void setUnidadeId(Long unidadeId) {
        this.unidadeId = unidadeId;
    }

    public String getUnidadeNome() {
        return unidadeNome;
    }

    public void setUnidadeNome(String unidadeNome) {
        this.unidadeNome = unidadeNome;
    }
}
