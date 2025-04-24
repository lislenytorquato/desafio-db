package com.DesafioDB.desafioDB.service;

import com.DesafioDB.desafioDB.dto.EnderecoDto;
import com.DesafioDB.desafioDB.dto.EnderecoRecebidoDto;
import com.DesafioDB.desafioDB.exceptions.EnderecoNaoEncontradoException;
import com.DesafioDB.desafioDB.helper.PessoaParaTestes;
import com.DesafioDB.desafioDB.mapper.EnderecoMapper;
import com.DesafioDB.desafioDB.model.Endereco;
import com.DesafioDB.desafioDB.repository.EnderecoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.DesafioDB.desafioDB.helper.EnderecoParaTestes.criarEndereco1;
import static com.DesafioDB.desafioDB.helper.EnderecoParaTestes.criarEndereco2;
import static com.DesafioDB.desafioDB.helper.PessoaParaTestes.ID_ENDERECO_PRINCIPAL_1;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EnderecoServiceTest {

    @InjectMocks
    EnderecoService enderecoService;

    @Mock
    EnderecoRepository enderecoRepository;

    @DisplayName("1-Deveria criar endereco e retornar enderecoDto")
    @Test
    public void deveriaCriarEnderecoERetornarEnderecoDto(){
        Endereco endereco = criarEndereco1();

        doReturn(endereco).when(enderecoRepository).save(any(Endereco.class));

        EnderecoRecebidoDto enderecoRecebidoDto = EnderecoMapper.mapEnderecoParaEnderecoRecebidoDto(endereco);

        EnderecoDto enderecoDto = enderecoService.criarEndereco(enderecoRecebidoDto);

        assertEquals(endereco.getId(),enderecoDto.getId());
        assertEquals(endereco.getRua(),enderecoDto.getRua());
        assertEquals(endereco.getNumero(),enderecoDto.getNumero());
        assertEquals(endereco.getBairro(),enderecoDto.getBairro());
        assertEquals(endereco.getCidade(),enderecoDto.getCidade());
        assertEquals(endereco.getEstado(),enderecoDto.getEstado());
        assertEquals(endereco.getEstado(),enderecoDto.getEstado());
        assertNotNull(enderecoDto);

    }

    @DisplayName("2-Deveria listar enderecos")
    @Test
    public void deveriaRetornarListaDeEndereco(){
        List<Endereco> listaDeEnderecos = List.of(criarEndereco1(), criarEndereco2());
        doReturn(listaDeEnderecos).when(enderecoRepository).findAll();
        List<EnderecoDto> listaDeEnderecoDto = enderecoService.listarEndereco();

        assertEquals(listaDeEnderecos.size(),listaDeEnderecoDto.size());
        assertEquals(listaDeEnderecos.get(0).getId(),listaDeEnderecoDto.get(0).getId());
        assertEquals(listaDeEnderecos.get(0).getRua(),listaDeEnderecoDto.get(0).getRua());
        assertEquals(listaDeEnderecos.get(0).getNumero(),listaDeEnderecoDto.get(0).getNumero());
        assertEquals(listaDeEnderecos.get(0).getBairro(),listaDeEnderecoDto.get(0).getBairro());
        assertEquals(listaDeEnderecos.get(0).getCidade(),listaDeEnderecoDto.get(0).getCidade());
        assertEquals(listaDeEnderecos.get(0).getEstado(),listaDeEnderecoDto.get(0).getEstado());
        assertEquals(listaDeEnderecos.get(1).getCep(),listaDeEnderecoDto.get(1).getCep());
        assertEquals(listaDeEnderecos.get(1).getId(),listaDeEnderecoDto.get(1).getId());
        assertEquals(listaDeEnderecos.get(1).getRua(),listaDeEnderecoDto.get(1).getRua());
        assertEquals(listaDeEnderecos.get(1).getNumero(),listaDeEnderecoDto.get(1).getNumero());
        assertEquals(listaDeEnderecos.get(1).getBairro(),listaDeEnderecoDto.get(1).getBairro());
        assertEquals(listaDeEnderecos.get(1).getCidade(),listaDeEnderecoDto.get(1).getCidade());
        assertEquals(listaDeEnderecos.get(1).getEstado(),listaDeEnderecoDto.get(1).getEstado());
        assertEquals(listaDeEnderecos.get(1).getCep(),listaDeEnderecoDto.get(1).getCep());
    }
    @DisplayName("3-Deveria atualizar enderecos")
    @Test
    public void deveriaAtualizarEndereco() {
        Endereco endereco = criarEndereco1();

        doReturn(endereco).when(enderecoRepository).save(any(Endereco.class));

        EnderecoRecebidoDto enderecoRecebidoDto = EnderecoMapper.mapEnderecoParaEnderecoRecebidoDto(endereco);

        EnderecoDto enderecoDto = enderecoService.criarEndereco(enderecoRecebidoDto);

        List<Endereco> listaDeEnderecos = List.of(endereco);

        doReturn(listaDeEnderecos).when(enderecoRepository).findAll();

        List<EnderecoDto> listaDeEnderecoDto = enderecoService.listarEndereco();

        assertEquals(enderecoDto.getId(),listaDeEnderecoDto.get(0).getId());
        assertEquals(enderecoDto.getRua(),listaDeEnderecoDto.get(0).getRua());
        assertEquals(enderecoDto.getNumero(),listaDeEnderecoDto.get(0).getNumero());
        assertEquals(enderecoDto.getBairro(),listaDeEnderecoDto.get(0).getBairro());
        assertEquals(enderecoDto.getCidade(),listaDeEnderecoDto.get(0).getCidade());
        assertEquals(enderecoDto.getEstado(),listaDeEnderecoDto.get(0).getEstado());
        assertEquals(enderecoDto.getCep(),listaDeEnderecoDto.get(0).getCep());
    }

    @DisplayName("4-Deveria deletar todos os enderecos")
    @Test
    public void deveriaDeletarTodosEnderecos() {

        doNothing().when(enderecoRepository).deleteAll();
        enderecoService.deletarTodosEnderecos();
        verify(enderecoRepository, times(1)).deleteAll();
    }
    @DisplayName("5-Deveria retornar endereco principal")
    @Test
    public void deveriaRetornarEnderecoPrincipal() throws EnderecoNaoEncontradoException {
        Endereco enderecoPrincipal = criarEndereco1();
        Optional<Endereco> enderecoPrincipalOptional = Optional.of(enderecoPrincipal);
        Long idEnderecoPrincipal = Long.parseLong(ID_ENDERECO_PRINCIPAL_1);

        doReturn(enderecoPrincipalOptional).when(enderecoRepository).findById(idEnderecoPrincipal);
        EnderecoDto enderecoPrincipalDto = enderecoService.enderecoPrincipal(idEnderecoPrincipal);

        assertEquals(enderecoPrincipal.getId(),enderecoPrincipalDto.getId());
        assertEquals(enderecoPrincipal.getRua(),enderecoPrincipalDto.getRua());
        assertEquals(enderecoPrincipal.getNumero(),enderecoPrincipalDto.getNumero());
        assertEquals(enderecoPrincipal.getBairro(),enderecoPrincipalDto.getBairro());
        assertEquals(enderecoPrincipal.getCidade(),enderecoPrincipalDto.getCidade());
        assertEquals(enderecoPrincipal.getEstado(),enderecoPrincipalDto.getEstado());
        assertEquals(enderecoPrincipal.getCep(),enderecoPrincipalDto.getCep());

    }
    @DisplayName("6-Deveria lancar excecao quando endereco nao fosse encontrado")
    @Test
    public void deveriaLancarExcecaoEnderecoNaoEncontrado() throws EnderecoNaoEncontradoException {

        Long idEnderecoPrincipal = Long.parseLong(ID_ENDERECO_PRINCIPAL_1);

        doReturn(Optional.empty()).when(enderecoRepository).findById(idEnderecoPrincipal);
        EnderecoNaoEncontradoException enderecoNaoEncontradoException = assertThrows(EnderecoNaoEncontradoException.class, () -> enderecoService.enderecoPrincipal(idEnderecoPrincipal));

        assertEquals(enderecoNaoEncontradoException.getMessage(), "Endereco nao encontrada");

    }
}
