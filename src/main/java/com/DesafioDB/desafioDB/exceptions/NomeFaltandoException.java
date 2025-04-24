package com.DesafioDB.desafioDB.exceptions;

public class NomeFaltandoException extends Exception{

    @Override
    public String getMessage(){
        return "Nome nao encontrado";
    }

}
