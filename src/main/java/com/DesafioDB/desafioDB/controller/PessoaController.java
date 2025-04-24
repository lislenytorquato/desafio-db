package com.DesafioDB.desafioDB.controller;

import com.DesafioDB.desafioDB.dto.EnderecoDto;
import com.DesafioDB.desafioDB.dto.PessoaDto;
import com.DesafioDB.desafioDB.dto.PessoaRecebidaDto;
import com.DesafioDB.desafioDB.exceptions.*;
import com.DesafioDB.desafioDB.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/pessoas")
public class PessoaController {

    @Autowired
    PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<PessoaDto> criarPessoa(@RequestBody PessoaRecebidaDto pessoaRecebidaDto) throws CpfFaltandoException, NomeFaltandoException, CpfIgualException {
        PessoaDto pessoa = pessoaService.criarPessoa(pessoaRecebidaDto);
        return new ResponseEntity<>(pessoa, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PessoaDto>> listarPessoas() {
        List<PessoaDto> pessoas = pessoaService.listarPessoas();

        return new ResponseEntity<>(pessoas, HttpStatus.OK);


    }

    @PutMapping("/{id}/endereco/{idEnderecoEscolhido}")
    public ResponseEntity<PessoaDto> atualizarPessoaEUmEnderecoEscolhido(@PathVariable Long idPessoa,
                                                     @RequestBody PessoaDto pessoaDto,
                                                     @PathVariable Long idEnderecoEscolhido) throws PessoaNaoEncontradaException, CpfFaltandoException, NomeFaltandoException, EnderecoNaoEncontradoException {
        PessoaDto pessoa = pessoaService.atualizarPessoa(idPessoa, pessoaDto,idEnderecoEscolhido);
        return new ResponseEntity<>(pessoa,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPessoa(@PathVariable Long id) throws PessoaNaoEncontradaException {
        pessoaService.deletarPessoa(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{idPessoa}/endereco-principal/{idEnderecoPrincipal}")
    public ResponseEntity<EnderecoDto> retornarEnderecoPrincipal(@PathVariable Long idPessoa, @PathVariable Long idEnderecoPrincipal) throws PessoaNaoEncontradaException, EnderecoNaoEncontradoException {
        EnderecoDto enderecoDto = pessoaService.retornarEnderecoPrincipal(idPessoa,idEnderecoPrincipal);
        return new ResponseEntity<>(enderecoDto,HttpStatus.OK);
    }

    @GetMapping("/{id}/idade")
    public ResponseEntity<Integer> retornarIdadePessoa(@PathVariable Long id) throws PessoaNaoEncontradaException {
        Integer idade = pessoaService.mostrarIdade(id);
        return new ResponseEntity<>(idade,HttpStatus.OK);
    }
}
