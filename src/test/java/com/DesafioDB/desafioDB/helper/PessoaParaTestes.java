package com.DesafioDB.desafioDB.helper;

import com.DesafioDB.desafioDB.dto.PessoaDto;
import com.DesafioDB.desafioDB.dto.PessoaRecebidaDto;
import com.DesafioDB.desafioDB.model.Pessoa;

import java.util.List;

import static com.DesafioDB.desafioDB.helper.EnderecoParaTestes.*;

public class PessoaParaTestes {
    public final static String ID_1 = "1";
    public final static String ID_2 = "2";
    public final static String NOME_1 = "Nome 1";
    public final static String NOME_2 = "Nome 2";
    public final static String CPF_1 = "01445678900";
    public final static String CPF_2 = "01589541233";
    public final static String DATA_NASCIMENTO_1 = "01/08/1978";
    public final static String DATA_NASCIMENTO_2 = "19/05/1988";
    public final static String ID_ENDERECO_PRINCIPAL_1 ="1";
    public final static String ID_ENDERECO_PRINCIPAL_2 ="2";

    public final static Pessoa criarPessoa1(){
        Pessoa pessoa1 = new Pessoa();

        pessoa1.setId(Long.parseLong(ID_1));
        pessoa1.setNome(NOME_1);
        pessoa1.setCpf(Long.parseLong(CPF_1));
        pessoa1.setDataNascimento(DATA_NASCIMENTO_1);

        pessoa1.setEnderecos(List.of(criarEndereco1()));
        return pessoa1;
    }
    public final static PessoaRecebidaDto criarPessoaRecebidaDtoSemNome(){
        PessoaRecebidaDto pessoaRecebidaDtoSemNome = new PessoaRecebidaDto();

        pessoaRecebidaDtoSemNome.setNome("");
        pessoaRecebidaDtoSemNome.setCpf(Long.parseLong(CPF_1));
        pessoaRecebidaDtoSemNome.setDataNascimento(DATA_NASCIMENTO_1);

        pessoaRecebidaDtoSemNome.setEnderecos(List.of(criarEndereco1()));
        return pessoaRecebidaDtoSemNome;
    }
    public final static PessoaRecebidaDto criarPessoaRecebidaDtoSemCpf(){
        PessoaRecebidaDto pessoaDtoSemNome = new PessoaRecebidaDto();

        pessoaDtoSemNome.setNome(NOME_1);
        pessoaDtoSemNome.setCpf(null);
        pessoaDtoSemNome.setDataNascimento(DATA_NASCIMENTO_1);

        pessoaDtoSemNome.setEnderecos(List.of(criarEndereco1()));
        return pessoaDtoSemNome;
    }
    public final static PessoaDto criarPessoaDto1(){
        PessoaDto pessoaDto1 = new PessoaDto();

        pessoaDto1.setId(Long.parseLong(ID_1));
        pessoaDto1.setNome(NOME_1);
        pessoaDto1.setCpf(Long.parseLong(CPF_1));
        pessoaDto1.setDataNascimento(DATA_NASCIMENTO_1);


        pessoaDto1.setEnderecos(List.of(criarEndereco1()));

        pessoaDto1.setIdEnderecoPrincipal(Long.parseLong(ID_ENDERECO_PRINCIPAL_1));
        return pessoaDto1;
    }
    public final static PessoaDto criarPessoaDto2(){
        PessoaDto pessoaDto2 = new PessoaDto();
        pessoaDto2.setId(Long.parseLong(ID_2));
        pessoaDto2.setNome(NOME_2);
        pessoaDto2.setCpf(Long.parseLong(CPF_2));
        pessoaDto2.setDataNascimento(DATA_NASCIMENTO_2);

        pessoaDto2.setEnderecos(List.of(criarEndereco2()));

        pessoaDto2.setIdEnderecoPrincipal(Long.parseLong(ID_ENDERECO_PRINCIPAL_2));
        return pessoaDto2;
    }
    public final static PessoaDto criarPessoaDtoSemNome(){
        PessoaDto pessoaDtoSemNome = new PessoaDto();

        pessoaDtoSemNome.setId(Long.parseLong(ID_1));
        pessoaDtoSemNome.setNome("");
        pessoaDtoSemNome.setCpf(Long.parseLong(CPF_1));
        pessoaDtoSemNome.setDataNascimento(DATA_NASCIMENTO_1);


        pessoaDtoSemNome.setEnderecos(List.of(criarEndereco1()));

        pessoaDtoSemNome.setIdEnderecoPrincipal(Long.parseLong(ID_ENDERECO_PRINCIPAL_1));
        return pessoaDtoSemNome;
    }
    public final static PessoaDto criarPessoaDtoSemCpf(){
        PessoaDto pessoaDtoSemCpf = new PessoaDto();

        pessoaDtoSemCpf.setId(Long.parseLong(ID_1));
        pessoaDtoSemCpf.setNome(NOME_1);
        pessoaDtoSemCpf.setCpf(null);
        pessoaDtoSemCpf.setDataNascimento(DATA_NASCIMENTO_1);


        pessoaDtoSemCpf.setEnderecos(List.of(criarEndereco1()));

        pessoaDtoSemCpf.setIdEnderecoPrincipal(Long.parseLong(ID_ENDERECO_PRINCIPAL_1));
        return pessoaDtoSemCpf;
    }
    public final static PessoaRecebidaDto criarPessoaRecebidaDto1(){

        PessoaRecebidaDto pessoaRecebidaDto1 = new PessoaRecebidaDto();
        pessoaRecebidaDto1.setNome(NOME_1);
        pessoaRecebidaDto1.setCpf(Long.parseLong(CPF_1));
        pessoaRecebidaDto1.setDataNascimento(DATA_NASCIMENTO_1);

        pessoaRecebidaDto1.setEnderecos(List.of(criarEndereco1()));
        pessoaRecebidaDto1.setIdEnderecoPrincipal(Long.parseLong(ID_ENDERECO_PRINCIPAL_1));

        return pessoaRecebidaDto1;
    }
    public final static PessoaRecebidaDto criarPessoaRecebidaDto2(){
        PessoaRecebidaDto pessoaRecebidaDto2 = new PessoaRecebidaDto();
        pessoaRecebidaDto2.setNome(NOME_2);
        pessoaRecebidaDto2.setCpf(Long.parseLong(CPF_2));
        pessoaRecebidaDto2.setDataNascimento(DATA_NASCIMENTO_2);
        pessoaRecebidaDto2.setEnderecos(List.of(criarEndereco2()));

        pessoaRecebidaDto2.setIdEnderecoPrincipal(Long.parseLong(ID_ENDERECO_PRINCIPAL_2));
        return pessoaRecebidaDto2;
    }
}
