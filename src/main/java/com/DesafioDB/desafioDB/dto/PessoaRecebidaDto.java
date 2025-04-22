package com.DesafioDB.desafioDB.dto;

import com.DesafioDB.desafioDB.model.Endereco;

import java.time.LocalDate;
import java.util.List;

public class PessoaRecebidaDto {


    private String nome;
    private Long cpf;
    private String dataNascimento;
    private List<Endereco> enderecos;
    private Long idEnderecoPrincipal;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    public List<Endereco> getEnderecos(){
        return enderecos;
    }
    public void setEnderecos(List<Endereco> enderecos){
        this.enderecos = enderecos;
    }
    public Long getIdEnderecoPrincipal() {
        return idEnderecoPrincipal;
    }

    public void setIdEnderecoPrincipal(Long idEnderecoPrincipal) {
        this.idEnderecoPrincipal = idEnderecoPrincipal;
    }
}
