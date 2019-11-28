package br.com.tt.petshop.exceptions;

public class RegistroNaoExisteException extends RuntimeException {
    public RegistroNaoExisteException(String message){
        super(message);
    }
}
