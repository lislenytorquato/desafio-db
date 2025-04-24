package com.DesafioDB.desafioDB.controller;

import com.DesafioDB.desafioDB.dto.EnderecoDto;
import com.DesafioDB.desafioDB.dto.PessoaDto;
import com.DesafioDB.desafioDB.dto.PessoaRecebidaDto;
import com.DesafioDB.desafioDB.exceptions.*;
import com.DesafioDB.desafioDB.model.Pessoa;
import com.DesafioDB.desafioDB.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping (value = "/pessoas")
public class PessoaController {

    @Autowired
    PessoaService pessoaService;

    @Operation(summary = "Cria uma pessoa")
    @ApiResponse(responseCode = "201",description = "pessoa criada")
    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<PessoaDto> criarPessoa(@RequestBody PessoaRecebidaDto pessoaRecebidaDto) throws CpfFaltandoException, NomeFaltandoException, CpfIgualException, EnderecoNaoEncontradoException {
        PessoaDto pessoa = pessoaService.criarPessoa(pessoaRecebidaDto);
        return new ResponseEntity<>(pessoa, HttpStatus.CREATED);
    }
    @Operation(summary = "Retorna uma lista de pessoas")
    @ApiResponse(responseCode = "200", description = "retorna lista de pessoas")
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<PessoaDto>> listarPessoas() {
        List<PessoaDto> pessoas = pessoaService.listarPessoas();

        return new ResponseEntity<>(pessoas, HttpStatus.OK);
    }

    @Operation(summary = "Atualiza uma pessoa e um endereco escolhido")
    @ApiResponse(responseCode = "200", description = "atualiza pessoa")
    @PutMapping(value = "/{id}/endereco/{idEnderecoEscolhido}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<PessoaDto> atualizarPessoaEUmEnderecoEscolhido(@PathVariable Long idPessoa,
                                                     @RequestBody PessoaDto pessoaDto,
                                                     @PathVariable Long idEnderecoEscolhido) throws PessoaNaoEncontradaException, CpfFaltandoException, NomeFaltandoException, EnderecoNaoEncontradoException {
        PessoaDto pessoa = pessoaService.atualizarPessoa(idPessoa, pessoaDto,idEnderecoEscolhido);
        return new ResponseEntity<>(pessoa,HttpStatus.OK);
    }

    @Operation(summary = "Deleta uma pessoa")
    @ApiResponse(responseCode = "204", description = "deleta pessoa")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletarPessoa(@PathVariable Long id) throws PessoaNaoEncontradaException {
        pessoaService.deletarPessoa(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Retorna o endereco principal de uma pessoa")
    @ApiResponse(responseCode = "200", description = "Retorna o endereco principal")
    @GetMapping(value = "/{idPessoa}/endereco-principal",produces = "application/json")
    public ResponseEntity<EnderecoDto> retornarEnderecoPrincipal(@PathVariable Long idPessoa) throws PessoaNaoEncontradaException, EnderecoNaoEncontradoException {
        EnderecoDto enderecoDto = pessoaService.retornarEnderecoPrincipal(idPessoa);
        return new ResponseEntity<>(enderecoDto,HttpStatus.OK);
    }

    @Operation(summary = "Retorna idade de uma pessoa")
    @ApiResponse(responseCode = "200", description = "Retorna idade")
    @GetMapping(value = "/{id}/idade", produces = "application/json")
    public ResponseEntity<Integer> retornarIdadePessoa(@PathVariable Long id) throws PessoaNaoEncontradaException {
        Integer idade = pessoaService.mostrarIdade(id);
        return new ResponseEntity<>(idade,HttpStatus.OK);
    }

    @Operation(summary = "Retorna lista de pessoa paginada")
    @ApiResponse(responseCode = "200", description = "Retorna a lista de pessoas paginada")
    @GetMapping(value = "{page}/{size}",produces = "application/json")
    public ResponseEntity<Page<Pessoa>> retornarPessoaPaginada(@PathVariable int page, @PathVariable int size){
        Page<Pessoa> pessoasPaginadas = pessoaService.retornarPessoasPaginadas(page, size);
        return new ResponseEntity<>(pessoasPaginadas,HttpStatus.OK);
    }
}
