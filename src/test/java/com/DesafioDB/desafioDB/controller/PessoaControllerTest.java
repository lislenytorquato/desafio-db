package com.DesafioDB.desafioDB.controller;

import com.DesafioDB.desafioDB.dto.PessoaDto;
import com.DesafioDB.desafioDB.helper.MethodsForTests;
import com.DesafioDB.desafioDB.helper.VariablesForTests;
import com.DesafioDB.desafioDB.service.PessoaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class PessoaControllerTest {

    @InjectMocks
    PessoaController pessoaController;

    @Mock
    PessoaService pessoaService;

    @Test
    public void shouldCreatePessoaReturnPessoaDtoAndStatusOK(){
         Mockito.when(pessoaService.createPessoa(MethodsForTests.criarPessoaRecebidaDto1())).thenReturn(MethodsForTests.criarPessoaDto1());

        ResponseEntity<PessoaDto> pessoa = pessoaController.createPessoa(MethodsForTests.criarPessoaRecebidaDto1());

        Assertions.assertEquals(pessoa,new ResponseEntity<>(MethodsForTests.criarPessoaDto1(), HttpStatus.CREATED));


    }
}
