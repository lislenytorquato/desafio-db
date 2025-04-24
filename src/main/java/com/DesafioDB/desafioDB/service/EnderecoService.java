package com.DesafioDB.desafioDB.service;

import com.DesafioDB.desafioDB.dto.EnderecoDto;
import com.DesafioDB.desafioDB.dto.EnderecoRecebidoDto;
import com.DesafioDB.desafioDB.exceptions.EnderecoNaoEncontradoException;
import com.DesafioDB.desafioDB.mapper.EnderecoMapper;
import com.DesafioDB.desafioDB.model.Endereco;
import com.DesafioDB.desafioDB.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public EnderecoDto criarEndereco(EnderecoRecebidoDto enderecoRecebidoDto) {

        Endereco endereco = new Endereco();

        endereco.setRua(enderecoRecebidoDto.getRua());
        endereco.setNumero(enderecoRecebidoDto.getNumero());
        endereco.setBairro(enderecoRecebidoDto.getBairro());
        endereco.setCidade(enderecoRecebidoDto.getCidade());
        endereco.setEstado(enderecoRecebidoDto.getEstado());
        endereco.setCep(enderecoRecebidoDto.getCep());

        Endereco enderecoSalvo = enderecoRepository.save(endereco);

        EnderecoDto enderecoDto = EnderecoMapper.mapEnderecoParaEnderecoDto(enderecoSalvo);
        return enderecoDto;
    }

    public List<EnderecoDto> listarEndereco(){

        List<Endereco> listaDeEndereco = enderecoRepository.findAll();

        return EnderecoMapper.mapListaDeEnderecoParaListaDeEnderecoDto(listaDeEndereco);
    }

    public List<EnderecoDto> atualizarEndereco( EnderecoRecebidoDto enderecoRecebidoDto) throws EnderecoNaoEncontradoException {

        this.criarEndereco(enderecoRecebidoDto);

        return this.listarEndereco();

    }

    public void deletarTodosEnderecos() {

        enderecoRepository.deleteAll();
    }

    public EnderecoDto enderecoPrincipal (Long idEnderecoPrincipal) throws EnderecoNaoEncontradoException {

        Optional<Endereco> enderecoPrincipal = enderecoRepository.findById(idEnderecoPrincipal);

        lancarExcecaoEnderecoNaoEncontrado(enderecoPrincipal);

        EnderecoDto enderecoPrincipalDto = EnderecoMapper.mapEnderecoParaEnderecoDto(enderecoPrincipal.get());

        return enderecoPrincipalDto;

    }
    private void lancarExcecaoEnderecoNaoEncontrado(Optional<Endereco> enderecoEscolhido) throws EnderecoNaoEncontradoException {
        if (enderecoEscolhido.isEmpty()){
            throw new EnderecoNaoEncontradoException();
        }
    }
}
