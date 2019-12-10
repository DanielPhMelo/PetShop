package br.com.tt.petshop.model.projection;

import br.com.tt.petshop.model.Animal;

import java.time.LocalDate;


public interface AnimalProjection {
    String getNome();
    LocalDate getDataNascimento();
}
