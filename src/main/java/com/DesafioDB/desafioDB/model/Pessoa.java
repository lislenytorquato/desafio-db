package com.DesafioDB.desafioDB.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;


import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
public class Pessoa {

    @Id @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column (nullable = false)
    private String nome;

    @NotNull
    @Column (nullable = false)
    private Long cpf;

    private String dataNascimento;

    @ManyToMany
    private List<Endereco> enderecos;

    private Long idEnderecoPrincipal;


    public Pessoa (){ }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getCpf(){
        return cpf;
    }

    public void setCpf(Long cpf){
        this.cpf = cpf;
    }

    public String getDataNascimento(){
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento){
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

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pessoa pessoa)) return false;
        return Objects.equals(id, pessoa.id) && Objects.equals(nome, pessoa.nome) && Objects.equals(cpf, pessoa.cpf) && Objects.equals(dataNascimento, pessoa.dataNascimento) && Objects.equals(enderecos, pessoa.enderecos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, cpf, dataNascimento, enderecos);
    }
}
