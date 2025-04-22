package com.DesafioDB.desafioDB.service;

import com.DesafioDB.desafioDB.dto.EnderecoDto;
import com.DesafioDB.desafioDB.dto.EnderecoRecebidoDto;
import com.DesafioDB.desafioDB.mapper.EnderecoMapper;
import com.DesafioDB.desafioDB.model.Endereco;
import com.DesafioDB.desafioDB.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {


    private EnderecoMapper enderecoMapper;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public EnderecoDto createEndereco(EnderecoRecebidoDto enderecoRecebidoDto) {


        Endereco endereco = new Endereco();
        endereco.setRua(enderecoRecebidoDto.getRua());
        endereco.setNumero(enderecoRecebidoDto.getNumero());
        endereco.setBairro(enderecoRecebidoDto.getBairro());
        endereco.setCidade(enderecoRecebidoDto.getCidade());
        endereco.setEstado(enderecoRecebidoDto.getEstado());
        endereco.setCep(enderecoRecebidoDto.getCep());

        Endereco enderecoSalvo = enderecoRepository.save(endereco);

        return EnderecoMapper.mapEnderecoParaEnderecoDto(enderecoSalvo);
    }

    public List<EnderecoDto> getEndereco(){

        List<Endereco> listaDeEndereco = enderecoRepository.findAll();

        return EnderecoMapper.mapListaDeEnderecoParaListaDeEnderecoDto(listaDeEndereco);
    }

    public List<EnderecoDto> updateEndereco(Long id, EnderecoRecebidoDto enderecoRecebidoDto){

        EnderecoDto enderecoDto = this.createEndereco(enderecoRecebidoDto);

        this.deleteEndereco(id);

        return this.getEndereco();

    }

    public void deleteEndereco(Long id){

        Optional<Endereco> enderecoById = enderecoRepository.findById(id);

        if (enderecoById.isEmpty()){
            throw new NullPointerException("endereco nao encontrado");
        }
        enderecoRepository.delete(enderecoById.get());
    }

    public EnderecoDto enderecoPrincipal (Long idEnderecoPrincipal){

        Optional<Endereco> enderecoPrincipal = enderecoRepository.findById(idEnderecoPrincipal);

        if (enderecoPrincipal.isEmpty()){
            throw new NullPointerException("endereco nao encontrado");
        }

        return EnderecoMapper.mapEnderecoParaEnderecoDto(enderecoPrincipal.get());

    }
}
