package com.DesafioDB.desafioDB.service;

import com.DesafioDB.desafioDB.dto.EnderecoDto;
import com.DesafioDB.desafioDB.dto.EnderecoRecebidoDto;
import com.DesafioDB.desafioDB.dto.PessoaDto;
import com.DesafioDB.desafioDB.dto.PessoaRecebidaDto;
import com.DesafioDB.desafioDB.exceptions.*;
import com.DesafioDB.desafioDB.mapper.EnderecoMapper;
import com.DesafioDB.desafioDB.mapper.PessoaMapper;
import com.DesafioDB.desafioDB.model.Endereco;
import com.DesafioDB.desafioDB.model.Pessoa;
import com.DesafioDB.desafioDB.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private EnderecoService enderecoService;

    public PessoaDto criarPessoa(PessoaRecebidaDto pessoaRecebidaDto) throws NomeFaltandoException, CpfFaltandoException, CpfIgualException {

        Pessoa pessoa = new Pessoa();

        lancarExcecaoNomeFaltando(pessoaRecebidaDto.getNome());
        pessoa.setNome(pessoaRecebidaDto.getNome());

        lancarExcecaoCpfFaltando(pessoaRecebidaDto.getCpf());
        lancarExcecaoCpfIgual(pessoaRecebidaDto.getCpf());
        pessoa.setCpf(pessoaRecebidaDto.getCpf());

        pessoa.setDataNascimento(pessoaRecebidaDto.getDataNascimento());

        List<EnderecoDto> enderecosCriados = new ArrayList<>();
        pessoaRecebidaDto.getEnderecos().forEach(enderecoRecebido -> {
            enderecosCriados.add(enderecoService.criarEndereco(EnderecoMapper.mapEnderecoParaEnderecoRecebidoDto(enderecoRecebido)));
        });
        pessoa.setEnderecos(EnderecoMapper.mapListaEnderecoDtoParaListaEndereco(enderecosCriados));

        pessoa.setIdEnderecoPrincipal(pessoaRecebidaDto.getIdEnderecoPrincipal());

        Pessoa pessoaSalva = pessoaRepository.save(pessoa);

        PessoaDto pessoaDto = PessoaMapper.mapPessoaParaPessoaDto(pessoaSalva);

        return pessoaDto;
    }

    public List<PessoaDto> listarPessoas(){
        List<Pessoa> pessoas = pessoaRepository.findAll();
        return PessoaMapper.mapListaDePessoaParaListaDePessoaDto(pessoas);

    }
    public PessoaDto atualizarPessoa(Long idPessoa, PessoaDto pessoaRecebida, Long idEnderecoASeratualizado) throws PessoaNaoEncontradaException, CpfFaltandoException, NomeFaltandoException, EnderecoNaoEncontradoException {
        pessoaRecebida.setId(idPessoa);

        Optional<Pessoa> pessoaParaAtualizar = pessoaRepository.findById(idPessoa);
        lancarExcecaoPessoaNaoEncontrada(pessoaParaAtualizar);

        lancarExcecaoCpfFaltando(pessoaRecebida.getCpf());
        pessoaParaAtualizar.get().setCpf(pessoaRecebida.getCpf());

        lancarExcecaoNomeFaltando(pessoaRecebida.getNome());
        pessoaParaAtualizar.get().setNome(pessoaRecebida.getNome());

        pessoaParaAtualizar.get().setDataNascimento(pessoaRecebida.getDataNascimento());

        List<Endereco> enderecosAtualizados = atualizarEConverterEnderecos(pessoaRecebida, idEnderecoASeratualizado);

        enderecosAtualizados.remove(idEnderecoASeratualizado.intValue()-1);

        pessoaParaAtualizar.get().setEnderecos(enderecosAtualizados);

        pessoaParaAtualizar.get().setIdEnderecoPrincipal(pessoaRecebida.getIdEnderecoPrincipal());

        Pessoa pessoaSalva = pessoaRepository.save(pessoaParaAtualizar.get());

        PessoaDto pessoaDto = PessoaMapper.mapPessoaParaPessoaDto(pessoaSalva);
        return pessoaDto;

    }
    public void deletarPessoa(Long id) {

        pessoaRepository.deleteById(id);
        enderecoService.deletarTodosEnderecos();
    }

    public EnderecoDto retornarEnderecoPrincipal(Long idPessoa, Long idEnderecoPrincipal) throws PessoaNaoEncontradaException, EnderecoNaoEncontradoException {
        Optional<Pessoa> pessoa = pessoaRepository.findById(idPessoa);

        lancarExcecaoPessoaNaoEncontrada(pessoa);

        return enderecoService.enderecoPrincipal(idEnderecoPrincipal);
    }
    public Integer mostrarIdade(Long id) throws PessoaNaoEncontradaException {

        Optional<Pessoa> pessoa = pessoaRepository.findById(id);

        lancarExcecaoPessoaNaoEncontrada(pessoa);

        String dataNascimento = pessoa.get().getDataNascimento();

        String[] numerosData = dataNascimento.split("/");

        int[] numerosDataConvertidos = new int[3];

        for (int i = 0; i<3; i++){
            numerosDataConvertidos[i] = Integer.parseInt(numerosData[i]);
        }
        int anoAtual = Calendar.getInstance().get(Calendar.YEAR);
        int anoNascimento = numerosDataConvertidos[2];

        return Integer.valueOf(anoAtual - anoNascimento);

    }
    private List<Endereco> atualizarEConverterEnderecos(PessoaDto pessoaRecebida, Long idEnderecoASerAtualizado) throws EnderecoNaoEncontradoException {

        List<EnderecoDto> enderecoDtoLista = new ArrayList<>();
        Endereco enderecoAtualizado = pessoaRecebida.getEnderecos().get(idEnderecoASerAtualizado.intValue()-1);
        EnderecoRecebidoDto enderecoRecebidoDto = EnderecoMapper.mapEnderecoParaEnderecoRecebidoDto(enderecoAtualizado);
        List<EnderecoDto> listaDeEnderecoDto = enderecoService.atualizarEndereco(enderecoRecebidoDto);

        List<Endereco> enderecos = EnderecoMapper.mapListaEnderecoDtoParaListaEndereco(listaDeEnderecoDto);

        return enderecos;
    }
    private void lancarExcecaoPessoaNaoEncontrada(Optional<Pessoa> pessoa) throws PessoaNaoEncontradaException {

        if (pessoa.isEmpty()) {
            throw new PessoaNaoEncontradaException();
        }
    }
    private void lancarExcecaoCpfFaltando(Long cpf) throws CpfFaltandoException {
        if (Objects.isNull(cpf)) {
            throw new CpfFaltandoException();
        }
    }
    private void lancarExcecaoNomeFaltando(String nome) throws NomeFaltandoException {
        if (nome.isEmpty()) {
            throw new NomeFaltandoException();
        }
    }
    private void lancarExcecaoCpfIgual(Long cpf) throws CpfIgualException {
        Optional<Pessoa> pessoa = pessoaRepository.findByCpf(cpf);
        if (pessoa.isPresent()){
            throw new CpfIgualException();
        }
    }
}
