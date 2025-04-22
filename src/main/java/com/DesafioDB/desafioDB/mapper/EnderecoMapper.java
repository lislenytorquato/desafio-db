package com.DesafioDB.desafioDB.mapper;

import com.DesafioDB.desafioDB.dto.EnderecoDto;
import com.DesafioDB.desafioDB.dto.EnderecoRecebidoDto;
import com.DesafioDB.desafioDB.model.Endereco;


import java.util.ArrayList;
import java.util.List;


public class EnderecoMapper {

    public static EnderecoDto mapEnderecoParaEnderecoDto(Endereco endereco){

       return criarEnderecoDto(endereco);
    }

    public static List<EnderecoDto> mapListaDeEnderecoParaListaDeEnderecoDto(List<Endereco> enderecos){
      EnderecoDto enderecoDto = null;
      List<EnderecoDto> listaDeEnderecoDto = new ArrayList<>();
        enderecos.forEach(endereco -> {
            listaDeEnderecoDto.add(criarEnderecoDto(endereco));
      });

        return listaDeEnderecoDto;

    }
    public static List<EnderecoRecebidoDto> mapListaDeEnderecoParaListaDeEnderecoRecebidoDto(List<Endereco> enderecos){
        EnderecoRecebidoDto enderecoRecebidoDto = null;
        List<EnderecoRecebidoDto> listaDeEnderecoRecebidoDto = new ArrayList<>();
        enderecos.forEach(endereco -> {
            listaDeEnderecoRecebidoDto.add(criarEnderecoRecebidoDto(endereco));
        });

        return listaDeEnderecoRecebidoDto;

    }
    public static EnderecoRecebidoDto mapEnderecoParaEnderecoRecebidoDto(Endereco endereco){

        return criarEnderecoRecebidoDto(endereco);
    }

    public static Endereco mapEnderecoDtoParaEndereco(EnderecoDto enderecoDto){

        return criarEndereco(enderecoDto);
    }
    public static List<Endereco> mapListaEnderecoDtoParaListaEndereco(List<EnderecoDto> enderecosDto){

        Endereco endereco = null;
        List<Endereco> enderecoList = new ArrayList<>();
        enderecosDto.forEach(enderecoDto -> {
            enderecoList.add(criarEndereco(enderecoDto));
        });

        return enderecoList;
    }

    private static EnderecoDto criarEnderecoDto( Endereco endereco){
        EnderecoDto enderecoDto = new EnderecoDto();
        enderecoDto.setId(endereco.getId());
        enderecoDto.setRua(endereco.getRua());
        enderecoDto.setBairro(endereco.getBairro());
        enderecoDto.setNumero(endereco.getNumero());
        enderecoDto.setCidade(endereco.getCidade());
        enderecoDto.setEstado(endereco.getEstado());
        enderecoDto.setCep(endereco.getCep());

        return enderecoDto;
    }
    private static EnderecoRecebidoDto criarEnderecoRecebidoDto( Endereco endereco){
        EnderecoRecebidoDto enderecoRecebidoDto = new EnderecoRecebidoDto();
        enderecoRecebidoDto.setRua(endereco.getRua());
        enderecoRecebidoDto.setBairro(endereco.getBairro());
        enderecoRecebidoDto.setNumero(endereco.getNumero());
        enderecoRecebidoDto.setCidade(endereco.getCidade());
        enderecoRecebidoDto.setEstado(endereco.getEstado());
        enderecoRecebidoDto.setCep(endereco.getCep());

        return enderecoRecebidoDto;
    }

    private static Endereco criarEndereco(EnderecoDto enderecoDto){
        Endereco endereco = new Endereco();
        endereco.setId(enderecoDto.getId());
        endereco.setRua(enderecoDto.getRua());
        endereco.setNumero(enderecoDto.getNumero());
        endereco.setBairro(enderecoDto.getBairro());
        endereco.setCidade(enderecoDto.getCidade());
        endereco.setEstado(enderecoDto.getEstado());
        endereco.setCep(enderecoDto.getCep());

        return endereco;

    }
}
