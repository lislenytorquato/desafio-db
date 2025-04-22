package com.DesafioDB.desafioDB.model;

import jakarta.persistence.*;


import java.util.Objects;


@Entity
//@Builder
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String rua;
    private Integer numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;

    public Endereco(){}

    public Endereco(Long id, String rua, Integer numero,
                    String bairro,String cidade,
                    String estado, String cep){
        this.setId(id);
        this.setRua(rua);
        this.setNumero(numero);
        this.setBairro(bairro);
        this.setCidade(cidade);
        this.setEstado(estado);
        this.setCep(cep);
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }
    public String getRua (){
        return rua;
    }
    public void setRua(String rua){
        this.rua = rua;
    }
    public Integer getNumero (){
        return numero;
    }
    public void setNumero(Integer numero){
        this.numero = numero;
    }
    public String getBairro (){
        return bairro;
    }
    public void setBairro(String bairro){
        this.bairro = bairro;
    }
    public String getCidade (){
        return cidade;
    }
    public void setCidade(String cidade){
        this.cidade = cidade;
    }
    public String getEstado (){
        return estado;
    }
    public void setEstado(String estado){
        this.estado = estado;
    }
    public String getCep(){
        return cep;
    }
    public void setCep(String cep){
        this.cep = cep;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Endereco endereco)) return false;
        return Objects.equals(id, endereco.id) && Objects.equals(rua, endereco.rua) && Objects.equals(numero, endereco.numero) && Objects.equals(bairro, endereco.bairro) && Objects.equals(cidade, endereco.cidade) && Objects.equals(estado, endereco.estado) && Objects.equals(cep, endereco.cep);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rua, numero, bairro, cidade, estado, cep);
    }
}
