package com.DesafioDB.desafioDB.exceptions.exceptionshandler;

import com.DesafioDB.desafioDB.exceptions.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @ApiResponse(responseCode = "404", description = "pessoa nao encontrada")
    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<String> pessoaNaoEncontradaExceptionHandler(PessoaNaoEncontradaException pessoaNaoEncontradaException){
        return new ResponseEntity<>(pessoaNaoEncontradaException.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ApiResponse(responseCode = "404", description = "endereco nao encontrado")
    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<String> enderecoNaoEncontradoExceptionHandler(EnderecoNaoEncontradoException enderecoNaoEncontradoException){
        return new ResponseEntity<>(enderecoNaoEncontradoException.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ApiResponse(responseCode = "400", description = "cpf esta faltando")
    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<String> cpfFaltandoExceptionHandler(CpfFaltandoException cpfFaltandoException){
        return new ResponseEntity<>(cpfFaltandoException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ApiResponse(responseCode = "400", description = "nome esta faltando")
    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<String> nomeFaltandoExceptionHandler(NomeFaltandoException nomeFaltandoException){
        return new ResponseEntity<>(nomeFaltandoException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ApiResponse(responseCode = "400", description = "cpf ja existe")
    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<String> cpfIgualExceptionHandler(CpfIgualException cpfIgualException){
        return new ResponseEntity<>(cpfIgualException.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
