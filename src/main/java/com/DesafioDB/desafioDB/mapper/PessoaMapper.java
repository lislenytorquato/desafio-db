package com.DesafioDB.desafioDB.mapper;


import com.DesafioDB.desafioDB.dto.PessoaDto;
import com.DesafioDB.desafioDB.model.Pessoa;


import java.util.ArrayList;
import java.util.List;



public class PessoaMapper {

    public static PessoaDto mapPessoaParaPessoaDto(Pessoa pessoa){

        return criarPessoaDto(pessoa);
    }

    public static List<PessoaDto> mapListaDePessoaParaListaDePessoaDto(List<Pessoa> pessoas){
        PessoaDto pessoaDto = null;
        List<PessoaDto> pessoaDtoList = new ArrayList<>();
        pessoas.forEach(pessoa -> {
            pessoaDtoList.add(criarPessoaDto(pessoa));
        });

        return pessoaDtoList;
    }

    private static PessoaDto criarPessoaDto(Pessoa pessoa){
        PessoaDto pessoaDto = new PessoaDto();
        pessoaDto.setId(pessoa.getId());
        pessoaDto.setNome(pessoa.getNome());
        pessoaDto.setCpf(pessoa.getCpf());
        pessoaDto.setDataNascimento(pessoa.getDataNascimento());
        pessoaDto.setEnderecos(pessoa.getEnderecos());
        pessoaDto.setIdEnderecoPrincipal(pessoa.getIdEnderecoPrincipal());
        return pessoaDto;
    }

}
