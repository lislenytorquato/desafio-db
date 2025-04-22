package com.DesafioDB.desafioDB.service;

import com.DesafioDB.desafioDB.dto.EnderecoDto;
import com.DesafioDB.desafioDB.dto.EnderecoRecebidoDto;
import com.DesafioDB.desafioDB.dto.PessoaDto;
import com.DesafioDB.desafioDB.dto.PessoaRecebidaDto;
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

    private PessoaMapper pessoaMapper;

    private EnderecoMapper enderecoMapper;

    public PessoaDto createPessoa(PessoaRecebidaDto pessoaRecebidaDto){

        Pessoa pessoa = new Pessoa();

        if (Objects.isNull(pessoaRecebidaDto.getNome())) {
            throw new NullPointerException("Nome da pessoa nao encontrado");
        }
        pessoa.setNome(pessoaRecebidaDto.getNome());

        if (Objects.isNull(pessoaRecebidaDto.getCpf())) {
            throw new NullPointerException("Cpf da pessoa nao encontrado");
        }
        pessoa.setCpf(pessoaRecebidaDto.getCpf());

        pessoa.setDataNascimento(pessoaRecebidaDto.getDataNascimento());

        List<EnderecoDto> enderecosCriados = new ArrayList<>();
        pessoaRecebidaDto.getEnderecos().forEach(enderecoRecebido -> {
            enderecosCriados.add(enderecoService.createEndereco(EnderecoMapper.mapEnderecoParaEnderecoRecebidoDto(enderecoRecebido)));
        });
        pessoa.setEnderecos(EnderecoMapper.mapListaEnderecoDtoParaListaEndereco(enderecosCriados));

        pessoa.setIdEnderecoPrincipal(pessoaRecebidaDto.getIdEnderecoPrincipal());

        Pessoa pessoaSalva = pessoaRepository.save(pessoa);

        return PessoaMapper.mapPessoaParaPessoaDto(pessoaSalva);
    }

    public List<PessoaDto> getPessoas(){
        List<Pessoa> pessoas = pessoaRepository.findAll();
        return PessoaMapper.mapListaDePessoaParaListaDePessoaDto(pessoas);

    }
    public PessoaDto updatePessoa(Long id, PessoaDto pessoaRecebida, Long idEnderecoRecebidoDto){
        pessoaRecebida.setId(id);

        Optional<Pessoa> pessoaParaAtualizar = pessoaRepository.findById(id);
        lancarExcecaoPessoaNaoEncontrada(pessoaParaAtualizar);

        if (Objects.isNull(pessoaRecebida.getCpf())) {
            throw new NullPointerException("Cpf da pessoa nao encontrado");
        }
        pessoaParaAtualizar.get().setCpf(pessoaRecebida.getCpf());

        if (Objects.isNull(pessoaRecebida.getNome())) {
            throw new NullPointerException("Nome da pessoa nao encontrado");
        }
        pessoaParaAtualizar.get().setNome(pessoaRecebida.getNome());

        pessoaParaAtualizar.get().setDataNascimento(pessoaRecebida.getDataNascimento());

        List<Endereco> enderecosAtualizados = atualizarEnderecos(pessoaRecebida, idEnderecoRecebidoDto);

        pessoaParaAtualizar.get().setEnderecos(enderecosAtualizados);

        if (pessoaRecebida.getNome() != null) {
            pessoaParaAtualizar.get().setIdEnderecoPrincipal(pessoaRecebida.getIdEnderecoPrincipal());
        }

        Pessoa pessoaSalva = pessoaRepository.save(pessoaParaAtualizar.get());

        return PessoaMapper.mapPessoaParaPessoaDto(pessoaSalva);

    }
    public void deletePessoa(Long id){
        Optional<Pessoa> pessoaEscolhida = pessoaRepository.findById(id);
        lancarExcecaoPessoaNaoEncontrada(pessoaEscolhida);
        pessoaRepository.delete(pessoaEscolhida.get());
    }

    public EnderecoDto retornarEnderecoPrincipal(Long idPessoa, Long idEnderecoPrincipal) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(idPessoa);

        lancarExcecaoPessoaNaoEncontrada(pessoa);

        return enderecoService.enderecoPrincipal(idEnderecoPrincipal);
    }
    public Integer mostrarIdade(Long id) {

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
    private List<Endereco> atualizarEnderecos(PessoaDto pessoaRecebida, Long idEnderecoRecebidoDto){

        List<EnderecoDto> enderecoDtoLista = new ArrayList<>();
        Endereco enderecoAtualizado = pessoaRecebida.getEnderecos().get(idEnderecoRecebidoDto.intValue()-1);
        EnderecoRecebidoDto enderecoRecebidoDto = EnderecoMapper.mapEnderecoParaEnderecoRecebidoDto(enderecoAtualizado);
        List<EnderecoDto> listaDeEnderecoDto = enderecoService.updateEndereco(idEnderecoRecebidoDto, enderecoRecebidoDto);

        List<Endereco> enderecos = EnderecoMapper.mapListaEnderecoDtoParaListaEndereco(listaDeEnderecoDto);

        return enderecos;
    }
    private void lancarExcecaoPessoaNaoEncontrada(Optional<Pessoa> pessoa){
        if (pessoa.isEmpty()){
            throw new NullPointerException("pessoa nao encontrada");
        }
    }
}
