package com.joaoandrade.deliveryfood.domain.service.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joaoandrade.deliveryfood.domain.exception.EntidadeNaoEncontradaException;
import com.joaoandrade.deliveryfood.domain.model.Usuario;
import com.joaoandrade.deliveryfood.domain.repository.UsuarioRepository;

@Service
public class CrudUsuarioService {

	@Autowired
	private UsuarioRepository repository;

	public Usuario buscarPorId(Long id) {
		return this.repository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(
				String.format("O usuario de id %d não foi encontrado no sistema!", id)));
	}

	@Transactional
	public Usuario cadastrar(Usuario usuario) {
		return this.repository.save(usuario);
	}

	@Transactional
	public Usuario atualizar(Usuario usuario) {
		return this.repository.save(usuario);
	}

	@Transactional
	public void deletarPorId(Long id) {
		this.buscarPorId(id);

		this.repository.deleteById(id);
	}

}
