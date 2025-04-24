package com.DesafioDB.desafioDB.exceptions;

public class EnderecoNaoEncontradoException extends Exception{

    @Override
    public String getMessage(){
        return "Endereco nao encontrada";
    }

}
