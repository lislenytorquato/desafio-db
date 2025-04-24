package com.DesafioDB.desafioDB.exceptions;

public class CpfIgualException extends Exception{

    @Override
    public String getMessage(){
        return "CPF ja existe";
    }

}
