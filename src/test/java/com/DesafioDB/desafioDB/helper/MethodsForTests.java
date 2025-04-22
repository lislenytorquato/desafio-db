package com.DesafioDB.desafioDB.helper;

import com.DesafioDB.desafioDB.dto.PessoaDto;
import com.DesafioDB.desafioDB.dto.PessoaRecebidaDto;
import com.DesafioDB.desafioDB.model.Endereco;

import java.util.List;

public class MethodsForTests {
    public static Endereco criarEndereco1(){
        Endereco endereco1 = new Endereco();
        endereco1.setId(Long.parseLong(VariablesForTests.ID_ENDERECO_1));
        endereco1.setRua(VariablesForTests.RUA_1);
        endereco1.setNumero(Integer.parseInt(VariablesForTests.NUMERO_1));
        endereco1.setBairro(VariablesForTests.BAIRRO_1);
        endereco1.setCidade(VariablesForTests.CIDADE_1);
        endereco1.setEstado(VariablesForTests.ESTADO_1);
        endereco1.setCep(VariablesForTests.CEP_1);

        return endereco1;
    }
    public static Endereco criarEndereco2(){
        Endereco endereco2 = new Endereco();
        endereco2.setId(Long.parseLong(VariablesForTests.ID_ENDERECO_2));
        endereco2.setRua(VariablesForTests.RUA_2);
        endereco2.setNumero(Integer.parseInt(VariablesForTests.NUMERO_2));
        endereco2.setBairro(VariablesForTests.BAIRRO_2);
        endereco2.setCidade(VariablesForTests.CIDADE_2);
        endereco2.setEstado(VariablesForTests.ESTADO_2);
        endereco2.setCep(VariablesForTests.CEP_2);

        return endereco2;
    }
    public static PessoaDto criarPessoaDto1(){
        PessoaDto pessoaDto1 = new PessoaDto();

        pessoaDto1.setId(Long.parseLong(VariablesForTests.ID_1));
        pessoaDto1.setNome(VariablesForTests.NOME_1);
        pessoaDto1.setCpf(Long.parseLong(VariablesForTests.CPF_1));
        pessoaDto1.setDataNascimento(VariablesForTests.DATA_NASCIMENTO_1);


        pessoaDto1.setEnderecos(List.of(criarEndereco1()));

        pessoaDto1.setIdEnderecoPrincipal(Long.parseLong(VariablesForTests.ID_ENDERECO_PRINCIPAL_1));
        return pessoaDto1;
    }
    public static PessoaDto criarPessoaDto2(){
        PessoaDto pessoaDto2 = new PessoaDto();
        pessoaDto2.setId(Long.parseLong(VariablesForTests.ID_1));
        pessoaDto2.setNome(VariablesForTests.NOME_1);
        pessoaDto2.setCpf(Long.parseLong(VariablesForTests.CPF_1));
        pessoaDto2.setDataNascimento(VariablesForTests.DATA_NASCIMENTO_1);

        pessoaDto2.setEnderecos(List.of(criarEndereco2()));

        pessoaDto2.setIdEnderecoPrincipal(Long.parseLong(VariablesForTests.ID_ENDERECO_PRINCIPAL_2));
        return pessoaDto2;
    }
    public static PessoaRecebidaDto criarPessoaRecebidaDto1(){

        PessoaRecebidaDto pessoaRecebidaDto1 = new PessoaRecebidaDto();
        pessoaRecebidaDto1.setNome(VariablesForTests.NOME_1);
        pessoaRecebidaDto1.setCpf(Long.parseLong(VariablesForTests.CPF_1));
        pessoaRecebidaDto1.setDataNascimento(VariablesForTests.DATA_NASCIMENTO_1);

        pessoaRecebidaDto1.setEnderecos(List.of(criarEndereco1()));
        pessoaRecebidaDto1.setIdEnderecoPrincipal(Long.parseLong(VariablesForTests.ID_ENDERECO_PRINCIPAL_1));

        return pessoaRecebidaDto1;
    }
    public static PessoaRecebidaDto pessoaRecebidaDto2(){
        PessoaRecebidaDto pessoaRecebidaDto2 = new PessoaRecebidaDto();
        pessoaRecebidaDto2.setNome(VariablesForTests.NOME_2);
        pessoaRecebidaDto2.setCpf(Long.parseLong(VariablesForTests.CPF_2));
        pessoaRecebidaDto2.setDataNascimento(VariablesForTests.DATA_NASCIMENTO_2);
        pessoaRecebidaDto2.setEnderecos(List.of(criarEndereco2()));

        pessoaRecebidaDto2.setIdEnderecoPrincipal(Long.parseLong(VariablesForTests.ID_ENDERECO_PRINCIPAL_2));
        return pessoaRecebidaDto2;
    }
}
