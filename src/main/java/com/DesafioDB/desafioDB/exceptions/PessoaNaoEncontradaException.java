package com.DesafioDB.desafioDB.exceptions;

public class PessoaNaoEncontradaException  extends Exception{

    @Override
    public String getMessage(){
        return "Pessoa nao encontrada";
    }

}
