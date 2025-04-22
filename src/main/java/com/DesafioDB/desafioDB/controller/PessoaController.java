package com.DesafioDB.desafioDB.controller;

import com.DesafioDB.desafioDB.dto.EnderecoDto;
import com.DesafioDB.desafioDB.dto.PessoaDto;
import com.DesafioDB.desafioDB.dto.PessoaRecebidaDto;
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
    public ResponseEntity<PessoaDto> createPessoa(@RequestBody PessoaRecebidaDto pessoaRecebidaDto){
        PessoaDto pessoa = pessoaService.createPessoa(pessoaRecebidaDto);
        return new ResponseEntity<>(pessoa, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PessoaDto>> getPessoas() {
        List<PessoaDto> pessoas = pessoaService.getPessoas();

        return new ResponseEntity<>(pessoas, HttpStatus.OK);


    }

    @PutMapping("/{id}/endereco/{idEndereco}")
    public ResponseEntity<PessoaDto> updatePessoa(@PathVariable Long id,
                                                  @RequestBody PessoaDto pessoaRecebidaDto,
                                                  @PathVariable Long idEndereco){
        PessoaDto pessoa = pessoaService.updatePessoa(id, pessoaRecebidaDto,idEndereco);
        return new ResponseEntity<>(pessoa,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePessoa(@PathVariable Long id){
        pessoaService.deletePessoa(id);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{idPessoa}/endereco-principal/{idEnderecoPrincipal}")
    public ResponseEntity<EnderecoDto> enderecoPrincipal(@PathVariable Long idPessoa, @PathVariable Long idEnderecoPrincipal){
        EnderecoDto enderecoDto = pessoaService.retornarEnderecoPrincipal(idPessoa,idEnderecoPrincipal);
        return new ResponseEntity<>(enderecoDto,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Integer>idadePessoa(@PathVariable Long id){
        Integer idade = pessoaService.mostrarIdade(id);
        return new ResponseEntity<>(idade,HttpStatus.OK);
    }
}
