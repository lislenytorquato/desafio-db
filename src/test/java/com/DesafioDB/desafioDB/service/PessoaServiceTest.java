package com.DesafioDB.desafioDB.service;

import com.DesafioDB.desafioDB.dto.EnderecoDto;
import com.DesafioDB.desafioDB.dto.EnderecoRecebidoDto;
import com.DesafioDB.desafioDB.dto.PessoaDto;
import com.DesafioDB.desafioDB.dto.PessoaRecebidaDto;
import com.DesafioDB.desafioDB.exceptions.*;
import com.DesafioDB.desafioDB.helper.EnderecoParaTestes;
import com.DesafioDB.desafioDB.mapper.EnderecoMapper;
import com.DesafioDB.desafioDB.model.Endereco;
import com.DesafioDB.desafioDB.model.Pessoa;
import com.DesafioDB.desafioDB.repository.PessoaRepository;
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

import java.util.List;
import java.util.Optional;

import static com.DesafioDB.desafioDB.helper.PessoaParaTestes.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PessoaServiceTest {

    @InjectMocks
    PessoaService pessoaService;

    @Mock
    PessoaRepository pessoaRepository;

    @Mock
    EnderecoService enderecoService;

    @DisplayName("1-Deveria criar pessoa")
    @Test
    public void deveriaCriarPessoa() throws CpfFaltandoException, CpfIgualException, NomeFaltandoException, EnderecoNaoEncontradoException {
        PessoaDto pessoaDto = criarPessoaDto1();
        Endereco enderecoPessoaDto = pessoaDto.getEnderecos().get(0);
        EnderecoDto enderecoDto = EnderecoMapper.mapEnderecoParaEnderecoDto(enderecoPessoaDto);

        Pessoa pessoa = criarPessoa1();

        doReturn(enderecoDto).when(enderecoService).criarEndereco(any(EnderecoRecebidoDto.class));
        doReturn(pessoa).when(pessoaRepository).save(any(Pessoa.class));

        PessoaDto pessoaCriada = pessoaService.criarPessoa(criarPessoaRecebidaDto1());

        assertEquals(pessoaDto.getId(),pessoaCriada.getId());
        assertEquals(pessoaDto.getNome(),pessoaCriada.getNome());
        assertEquals(pessoaDto.getCpf(),pessoaCriada.getCpf());
        assertEquals(pessoaDto.getDataNascimento(),pessoaCriada.getDataNascimento());
        assertEquals(pessoaDto.getEnderecos(),pessoaCriada.getEnderecos());
        assertEquals(pessoaDto.getIdEnderecoPrincipal(),pessoaCriada.getIdEnderecoPrincipal());

    }
    @DisplayName("2-Deveria lancar excecao Nome faltando quando criar pessoa sem nome")
    @Test
    public void deveriaLancarExcecaoNomeFaltandoQuandoCriarPessoaSemNome() throws CpfFaltandoException, CpfIgualException, NomeFaltandoException {
        PessoaRecebidaDto pessoaRecebidaDtoSemNome = criarPessoaRecebidaDtoSemNome();

        assertThrows(NomeFaltandoException.class,()->pessoaService.criarPessoa(pessoaRecebidaDtoSemNome));

        assertEquals(pessoaRecebidaDtoSemNome.getNome(),"");
    }
    @DisplayName("3-Deveria lancar excecao Cpf faltando quando criar pessoa sem cpf")
    @Test
    public void deveriaLancarExcecaoCpfFaltandoQuandoCriarPessoaSemCpf() throws CpfFaltandoException, CpfIgualException, NomeFaltandoException {
        PessoaRecebidaDto pessoaRecebidaDtoSemCpf = criarPessoaRecebidaDtoSemCpf();

        assertThrows(CpfFaltandoException.class,()->pessoaService.criarPessoa(pessoaRecebidaDtoSemCpf));
    }
    @DisplayName("4-Deveria lancar excecao Cpf igual quando criar pessoa com mesmo cpf")
    @Test
    public void deveriaLancarExcecaoCpfIgualQuandoCriarPessoaComCpfCadastrado() throws CpfFaltandoException, CpfIgualException, NomeFaltandoException {
        PessoaRecebidaDto pessoaRecebidaDto = criarPessoaRecebidaDto1();
        Pessoa pessoa = criarPessoa1();
        doReturn(Optional.of(pessoa)).when(pessoaRepository).findByCpf(pessoaRecebidaDto.getCpf());

        assertThrows(CpfIgualException.class,()->pessoaService.criarPessoa(pessoaRecebidaDto));
    }
    @DisplayName("5-Deveria listar pessoas")
    @Test
    public void deveriaListarPessoas(){
        Pessoa pessoa = criarPessoa1();
        List<Pessoa> listaPessoas = List.of(pessoa);
        doReturn(listaPessoas).when(pessoaRepository).findAll();
        List<PessoaDto> pessoaDtos = pessoaService.listarPessoas();

        assertEquals(listaPessoas.get(0).getId(),pessoaDtos.get(0).getId());
        assertEquals(listaPessoas.get(0).getCpf(),pessoaDtos.get(0).getCpf());
        assertEquals(listaPessoas.get(0).getNome(),pessoaDtos.get(0).getNome());
        assertEquals(listaPessoas.get(0).getDataNascimento(),pessoaDtos.get(0).getDataNascimento());
        assertEquals(listaPessoas.get(0).getEnderecos(),pessoaDtos.get(0).getEnderecos());
        assertEquals(listaPessoas.get(0).getIdEnderecoPrincipal(),pessoaDtos.get(0).getIdEnderecoPrincipal());

    }
    @DisplayName("6-Deveria deletar pessoas")
    @Test
    public void deveriaDeletarPessoas()  {
        Pessoa pessoa = criarPessoa1();
        long idPessoa = Long.parseLong(ID_1);

        doNothing().when(pessoaRepository).deleteById(idPessoa);
        doNothing().when(enderecoService).deletarTodosEnderecos();
        pessoaService.deletarPessoa(idPessoa);

        verify(pessoaRepository,times(1)).deleteById(idPessoa);
        verify(enderecoService,times(1)).deletarTodosEnderecos();
    }
    @DisplayName("7-Deveria retornar endereco principal")
    @Test
    public void deveriaRetornarEnderecoPrincipalDaPessoa() throws PessoaNaoEncontradaException, EnderecoNaoEncontradoException {
        Pessoa pessoa = criarPessoa1();
        Long idPessoa = Long.parseLong(ID_1);
        Long idEnderecoPrincipal = Long.parseLong(ID_ENDERECO_PRINCIPAL_1);

        EnderecoDto enderecoDto = EnderecoMapper.mapEnderecoParaEnderecoDto(pessoa.getEnderecos().get(0));

        doReturn(Optional.of(criarPessoa1())).when(pessoaRepository).findById(idPessoa);
        doReturn(enderecoDto).when(enderecoService).enderecoPrincipal(idEnderecoPrincipal);
        EnderecoDto enderecoPrincipalDto = pessoaService.retornarEnderecoPrincipal(idPessoa);

        assertEquals(enderecoPrincipalDto.getId(),idEnderecoPrincipal);
        assertNotNull(enderecoPrincipalDto);
    }
    @DisplayName("8-Deveria lancar excecao quando pessoa nao fosse encontrada no endereco principal")
    @Test
    public void deveriaLancarExcecaoPessoaNaoEncontradaEmEnderecoPrincipal(){

        Long idPessoa = Long.parseLong(ID_1);
        Long idEnderecoPrincipal = Long.parseLong(ID_ENDERECO_PRINCIPAL_1);

        doReturn(Optional.empty()).when(pessoaRepository).findById(idPessoa);

        assertThrows(PessoaNaoEncontradaException.class,()->pessoaService.retornarEnderecoPrincipal(idPessoa));
    }
    @DisplayName("9-Deveria mostrar idade")
    @Test
    public void deveriaMostrarIdade() throws PessoaNaoEncontradaException {
        Pessoa pessoa = criarPessoa1();
        doReturn(Optional.of(pessoa)).when(pessoaRepository).findById(pessoa.getId());
        Integer idade = pessoaService.mostrarIdade(pessoa.getId());
        assertEquals(idade,47);

    }
    @DisplayName("10-Deveria lancar excecao quando pessoa nao fosse encontrada ao mostrar idade")
    @Test
    public void deveriaLancarExcecaoPessoaNaoEncontradaAoMostrarIdade(){

        Long idPessoa = Long.parseLong(ID_1);

        doReturn(Optional.empty()).when(pessoaRepository).findById(idPessoa);

        assertThrows(PessoaNaoEncontradaException.class,()->pessoaService.mostrarIdade(idPessoa));
    }
    @DisplayName("11-Deveria atualizar pessoa")
    @Test
    public void deveriaAtualizarPessoa() throws EnderecoNaoEncontradoException, CpfFaltandoException, NomeFaltandoException, PessoaNaoEncontradaException {
        Long idPessoa = Long.parseLong(ID_1);
        Long idEnderecoASerAtualizado = Long.parseLong(EnderecoParaTestes.ID_ENDERECO_1);
        Pessoa pessoa = criarPessoa1();
        PessoaDto pessoaDto = criarPessoaDto1();

        doReturn(Optional.of(pessoa)).when(pessoaRepository).findById(idPessoa);

        EnderecoDto enderecoDto1 = EnderecoParaTestes.criarEnderecoDto1();

        EnderecoDto enderecoDto2 = EnderecoParaTestes.criarEnderecoDto2();

        List<EnderecoDto> listaEnderecoDto = doReturn(List.of(enderecoDto1,enderecoDto2)).when(enderecoService).atualizarEndereco(any(EnderecoRecebidoDto.class));

        doReturn(pessoa).when(pessoaRepository).save(any(Pessoa.class));
        PessoaDto pessoaAtualizada = pessoaService.atualizarPessoa(idPessoa, pessoaDto, idEnderecoASerAtualizado);

        assertEquals(pessoaAtualizada.getNome(), pessoaDto.getNome());
        assertEquals(pessoaAtualizada.getCpf(), pessoaDto.getCpf());
        assertEquals(pessoaAtualizada.getDataNascimento(), pessoaDto.getDataNascimento());
        assertNotEquals(pessoaAtualizada.getEnderecos(), pessoaDto.getEnderecos());
    }
    @DisplayName("12-Deveria lancar excecao quando pessoa nao fosse encontrada ao atualizar")
    @Test
    public void deveriaLancarExcecaoPessoaNaoEncontradaAoAtualizar(){

        Long idPessoa = Long.parseLong(ID_1);
        Long idEnderecoASerAtualizado = Long.parseLong(EnderecoParaTestes.ID_ENDERECO_1);
        PessoaDto pessoaDto = criarPessoaDto1();

        doReturn(Optional.empty()).when(pessoaRepository).findById(idPessoa);

        assertThrows(PessoaNaoEncontradaException.class,()->pessoaService.atualizarPessoa(idPessoa,pessoaDto,idEnderecoASerAtualizado));
    }
    @DisplayName("13-Deveria lancar excecao Nome faltando quando atualizar pessoa sem nome")
    @Test
    public void deveriaLancarExcecaoNomeFaltandoQuandoAtualizarPessoaSemNome() throws CpfFaltandoException, CpfIgualException, NomeFaltandoException {

        Long idPessoa = Long.parseLong(ID_1);
        Long idEnderecoASerAtualizado = Long.parseLong(EnderecoParaTestes.ID_ENDERECO_1);
        PessoaDto pessoaDtoSemNome = criarPessoaDtoSemNome();
        Pessoa pessoa = criarPessoa1();

        doReturn(Optional.of(pessoa)).when(pessoaRepository).findById(idPessoa);

        assertThrows(NomeFaltandoException.class,()->pessoaService.atualizarPessoa(idPessoa,pessoaDtoSemNome,idEnderecoASerAtualizado));

        assertEquals(pessoaDtoSemNome.getNome(),"");
    }
    @DisplayName("14-Deveria lancar excecao Cpf faltando quando atualizar pessoa sem cpf")
    @Test
    public void deveriaLancarExcecaoCpfFaltandoQuandoAtualizarPessoaSemCpf() throws CpfFaltandoException, CpfIgualException, NomeFaltandoException {
        PessoaDto pessoaDtoSemCpf = criarPessoaDtoSemCpf();
        Long idPessoa = Long.parseLong(ID_1);
        Long idEnderecoASerAtualizado = Long.parseLong(EnderecoParaTestes.ID_ENDERECO_1);
        Pessoa pessoa = criarPessoa1();

        doReturn(Optional.of(pessoa)).when(pessoaRepository).findById(idPessoa);

        assertThrows(CpfFaltandoException.class,()->pessoaService.atualizarPessoa(idPessoa,pessoaDtoSemCpf,idEnderecoASerAtualizado));
    }
    @DisplayName("15-Deveria retornar pessoa paginada")
    @Test
    public void deveriaRetornarPessoaPaginada(){
        int page = 0;
        int size = 1;
        List<Pessoa> listaPessoas = List.of(criarPessoa1());
        Page<Pessoa> pessoaPage = new PageImpl<>(listaPessoas);
        Pageable pageable = PageRequest.of(page,size);

        doReturn(pessoaPage).when(pessoaRepository).findAll(pageable);
        Page<Pessoa> pessoaPaginada = pessoaService.retornarPessoasPaginadas(page, size);

        assertEquals(pessoaPaginada, pessoaPage);
        assertEquals(pessoaPaginada.getSize(),size);
    }

}
