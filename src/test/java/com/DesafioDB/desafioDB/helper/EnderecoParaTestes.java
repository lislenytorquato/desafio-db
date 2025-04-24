package com.DesafioDB.desafioDB.helper;

import com.DesafioDB.desafioDB.dto.EnderecoDto;
import com.DesafioDB.desafioDB.dto.EnderecoRecebidoDto;
import com.DesafioDB.desafioDB.model.Endereco;

public class EnderecoParaTestes {

    public final static String ID_ENDERECO_1 ="1";
    public final static String RUA_1 = "rua 1";
    public final static String NUMERO_1 = "1500";
    public final static String BAIRRO_1 = "bairro 1";
    public final static String CIDADE_1 = "cidade 1";
    public final static String ESTADO_1 = "estado 1";
    public final static String CEP_1 = "12345678";
    public final static String ID_ENDERECO_2 ="2";
    public final static String RUA_2 = "rua 2";
    public final static String NUMERO_2 = "1250";
    public final static String BAIRRO_2 = "bairro 2";
    public final static String CIDADE_2 = "cidade 2";
    public final static String ESTADO_2 = "estado 2";
    public final static String CEP_2 = "2468101214";

    public final static Endereco criarEndereco1(){
        Endereco endereco1 = new Endereco();
        endereco1.setId(Long.parseLong(ID_ENDERECO_1));
        endereco1.setRua(RUA_1);
        endereco1.setNumero(Integer.parseInt(NUMERO_1));
        endereco1.setBairro(BAIRRO_1);
        endereco1.setCidade(CIDADE_1);
        endereco1.setEstado(ESTADO_1);
        endereco1.setCep(CEP_1);

        return endereco1;
    }
    public final static Endereco criarEndereco2(){
        Endereco endereco2 = new Endereco();
        endereco2.setId(Long.parseLong(ID_ENDERECO_2));
        endereco2.setRua(RUA_2);
        endereco2.setNumero(Integer.parseInt(NUMERO_2));
        endereco2.setBairro(BAIRRO_2);
        endereco2.setCidade(CIDADE_2);
        endereco2.setEstado(ESTADO_2);
        endereco2.setCep(CEP_2);

        return endereco2;
    }
    public final static EnderecoDto criarEnderecoDto1(){
        EnderecoDto enderecoDto1 = new EnderecoDto();
        enderecoDto1.setId(Long.parseLong(ID_ENDERECO_1));
        enderecoDto1.setRua(RUA_1);
        enderecoDto1.setNumero(Integer.parseInt(NUMERO_1));
        enderecoDto1.setBairro(BAIRRO_1);
        enderecoDto1.setCidade(CIDADE_1);
        enderecoDto1.setEstado(ESTADO_1);
        enderecoDto1.setCep(CEP_1);

        return enderecoDto1;
    }
    public final static EnderecoDto criarEnderecoDto2(){
        EnderecoDto enderecoDto2 = new EnderecoDto();
        enderecoDto2.setId(Long.parseLong(ID_ENDERECO_2));
        enderecoDto2.setRua(RUA_2);
        enderecoDto2.setNumero(Integer.parseInt(NUMERO_2));
        enderecoDto2.setBairro(BAIRRO_2);
        enderecoDto2.setCidade(CIDADE_2);
        enderecoDto2.setEstado(ESTADO_2);
        enderecoDto2.setCep(CEP_2);

        return enderecoDto2;
    }
    public final static EnderecoRecebidoDto criarEnderecoRecebidoDto1(){
        EnderecoRecebidoDto enderecoRecebidoDto1 = new EnderecoRecebidoDto();

        enderecoRecebidoDto1.setRua(RUA_1);
        enderecoRecebidoDto1.setNumero(Integer.parseInt(NUMERO_1));
        enderecoRecebidoDto1.setBairro(BAIRRO_1);
        enderecoRecebidoDto1.setCidade(CIDADE_1);
        enderecoRecebidoDto1.setEstado(ESTADO_1);
        enderecoRecebidoDto1.setCep(CEP_1);

        return enderecoRecebidoDto1;
    }
}
