package br.com.tt.petshop.dto;

import br.com.tt.petshop.model.Cliente;
import br.com.tt.petshop.model.Unidade;

import java.time.LocalDate;

public class AnimalOutDto {

    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    private String unidade;
    private String cliente;

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public AnimalOutDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}