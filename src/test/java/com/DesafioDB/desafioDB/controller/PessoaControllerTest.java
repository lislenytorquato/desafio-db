package com.DesafioDB.desafioDB.controller;

import com.DesafioDB.desafioDB.dto.EnderecoDto;
import com.DesafioDB.desafioDB.dto.PessoaDto;
import com.DesafioDB.desafioDB.dto.PessoaRecebidaDto;
import com.DesafioDB.desafioDB.exceptions.*;
import com.DesafioDB.desafioDB.model.Pessoa;
import com.DesafioDB.desafioDB.service.PessoaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static com.DesafioDB.desafioDB.helper.EnderecoParaTestes.criarEnderecoDto1;
import static com.DesafioDB.desafioDB.helper.PessoaParaTestes.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PessoaControllerTest {

    private final static int PAGE = 0;
    private final static int SIZE = 1;

    @InjectMocks
    PessoaController pessoaController;

    @Mock
    PessoaService pessoaService;

    @DisplayName("1-Deveria criar pessoa e retornar status created")
    @Test
    public void deveriaCriarPessoaERetornarStatusCreated() throws CpfFaltandoException, NomeFaltandoException, CpfIgualException, EnderecoNaoEncontradoException {
        PessoaRecebidaDto pessoaRecebidaDto1 = criarPessoaRecebidaDto1();
        PessoaDto pessoaDto1 = criarPessoaDto1();
        when(pessoaService.criarPessoa(pessoaRecebidaDto1)).thenReturn(pessoaDto1);

        ResponseEntity<PessoaDto> pessoaCriada = pessoaController.criarPessoa(pessoaRecebidaDto1);

        assertEquals(pessoaCriada, new ResponseEntity<>(pessoaDto1, HttpStatus.CREATED));
        assertEquals(pessoaCriada.getStatusCode(), HttpStatus.CREATED);

    }
    @DisplayName("2-Deveria criar listar pessoa e retornar status ok")
    @Test
    public void deveriaListarPessoaERetornarStatusOk() {
        List<PessoaDto> listaPessoas = List.of(criarPessoaDto1(), criarPessoaDto2());

        when(pessoaService.listarPessoas()).thenReturn(listaPessoas);

        ResponseEntity<List<PessoaDto>> pessoasListadas = pessoaController.listarPessoas();

        assertEquals(pessoasListadas, new ResponseEntity<>(listaPessoas, HttpStatus.OK));
        assertEquals(pessoasListadas.getStatusCode(), HttpStatus.OK);
    }

    @DisplayName("3-Deveria atualizar pessoa e um endereco escolhido e retornar status ok")
    @Test
    public void deveriaAtualizarPessoaEUmEnderecoEscolhidoERetornarStatusOk() throws CpfFaltandoException, NomeFaltandoException, EnderecoNaoEncontradoException, PessoaNaoEncontradaException {
        Long idPessoa = Long.parseLong(ID_1);
        Long idEnderecoASerAtualizado =  Long.parseLong(ID_1);
        PessoaDto pessoaDto = criarPessoaDto1();

        when(pessoaService.atualizarPessoa(idPessoa, pessoaDto, idEnderecoASerAtualizado)).thenReturn(pessoaDto);

        ResponseEntity<PessoaDto> pessoaAtualizada = pessoaController.atualizarPessoaEUmEnderecoEscolhido(idPessoa, pessoaDto, idEnderecoASerAtualizado);

        assertEquals(pessoaAtualizada, new ResponseEntity<>(pessoaDto, HttpStatus.OK));
        assertEquals(pessoaAtualizada.getStatusCode(), HttpStatus.OK);
    }

    @DisplayName("4-Deveria deletar pessoa e retornar status no content")
    @Test
    public void deveriaDeletarPessoaERetornarStatusNoContent() throws CpfFaltandoException, CpfIgualException, NomeFaltandoException, PessoaNaoEncontradaException {
        Long id = Long.parseLong(ID_1);
        doNothing().when(pessoaService).deletarPessoa(id);

        ResponseEntity<Void> pessoaDeletadaResposta = pessoaController.deletarPessoa(id);

        verify(pessoaService, times(1)).deletarPessoa(id);
        assertEquals(pessoaDeletadaResposta.getStatusCode(), HttpStatus.NO_CONTENT);

    }

    @DisplayName("5-Deveria retornar endereco principal e status ok")
    @Test
    public void deveriaRetornarEnderecoPrincipalEStatusOk() throws PessoaNaoEncontradaException, EnderecoNaoEncontradoException {

        Long idPessoa = Long.parseLong(ID_1);
        EnderecoDto enderecoDto1 = criarEnderecoDto1();

        when(pessoaService.retornarEnderecoPrincipal(idPessoa)).thenReturn(enderecoDto1);

        ResponseEntity<EnderecoDto> enderecoPrincipal = pessoaController.retornarEnderecoPrincipal(idPessoa);

        assertEquals(enderecoPrincipal, new ResponseEntity<>(enderecoDto1, HttpStatus.OK));
        assertEquals(enderecoPrincipal.getStatusCode(), HttpStatus.OK);
    }

    @DisplayName("6-Deveria retornar idade e status ok")
    @Test
    public void deveriaRetornarIdadeEStatusOk() throws PessoaNaoEncontradaException {
        Long idPessoa = criarPessoa1().getId();
        Integer idade = 31;

        when(pessoaService.mostrarIdade(idPessoa)).thenReturn(idade);
        ResponseEntity<Integer> idadePessoa = pessoaController.retornarIdadePessoa(idPessoa);

        assertEquals(idadePessoa, new ResponseEntity<>(idade, HttpStatus.OK));
        assertEquals(idadePessoa.getStatusCode(), HttpStatus.OK);

    }
    @DisplayName("7-Deveria retornar pessoas paginadas")
    @Test
    public void deveriaRetornarPessoasPaginadas(){
        List<Pessoa> listaPessoas = List.of(criarPessoa1());
        Page<Pessoa> pessoaPage = new PageImpl<>(listaPessoas);
        when(pessoaService.retornarPessoasPaginadas(PAGE,SIZE)).thenReturn(pessoaPage);
        ResponseEntity<Page<Pessoa>> pessoaPaginada = pessoaController.retornarPessoaPaginada(PAGE, SIZE);

        assertEquals(pessoaPaginada, new ResponseEntity<>(pessoaPage,HttpStatus.OK));
        assertEquals(pessoaPaginada.getStatusCode(), HttpStatus.OK);
    }
}
