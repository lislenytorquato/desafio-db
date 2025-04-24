package com.DesafioDB.desafioDB.exceptions;

public class CpfFaltandoException extends Exception{

    @Override
    public String getMessage(){
        return "CPF nao encontrado";
    }

}
