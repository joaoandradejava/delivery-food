package com.joaoandrade.deliveryfood.domain.service.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joaoandrade.deliveryfood.domain.exception.EntidadeNaoEncontradaException;
import com.joaoandrade.deliveryfood.domain.exception.NegocioException;
import com.joaoandrade.deliveryfood.domain.model.Endereco;
import com.joaoandrade.deliveryfood.domain.model.Usuario;
import com.joaoandrade.deliveryfood.domain.repository.EnderecoRepository;

@Service
public class CrudUsuarioEnderecoService {

	@Autowired
	private CrudUsuarioService crudUsuarioService;

	@Autowired
	private EnderecoRepository enderecoRepository;

	private Endereco buscarEnderecoPorId(Long enderecoId) {
		return this.enderecoRepository.findById(enderecoId).orElseThrow(() -> new EntidadeNaoEncontradaException(
				String.format("O endereço de id %d não foi encontrado no sistema!", enderecoId)));
	}

	public Endereco buscarEndereco(Long usuarioId, Long enderecoId) {
		this.crudUsuarioService.buscarPorId(usuarioId);
		this.buscarEnderecoPorId(enderecoId);

		return this.enderecoRepository.buscarEnderecoDoUsuario(usuarioId, enderecoId)
				.orElseThrow(() -> new NegocioException(String.format(
						"O endereço de id %d não está associado com o usuario de id %d", enderecoId, usuarioId)));
	}

	@Transactional
	public Endereco adicionarEndereco(Long uuarioId, Endereco endereco) {
		Usuario usuario = this.crudUsuarioService.buscarPorId(uuarioId);
		endereco.setUsuario(usuario);

		return this.enderecoRepository.save(endereco);
	}

	@Transactional
	public Endereco atualizar(Endereco endereco) {
		return this.enderecoRepository.save(endereco);
	}

	@Transactional
	public void deletarEnderecoDoUsuario(Long usuarioId, Long enderecoId) {
		this.buscarEndereco(usuarioId, enderecoId);

		this.enderecoRepository.deleteById(enderecoId);
	}

}
