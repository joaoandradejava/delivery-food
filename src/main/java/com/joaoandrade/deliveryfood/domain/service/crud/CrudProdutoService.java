package com.joaoandrade.deliveryfood.domain.service.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joaoandrade.deliveryfood.domain.exception.EntidadeEmUsoException;
import com.joaoandrade.deliveryfood.domain.exception.EntidadeNaoEncontradaException;
import com.joaoandrade.deliveryfood.domain.filter.ProdutoFilter;
import com.joaoandrade.deliveryfood.domain.model.Produto;
import com.joaoandrade.deliveryfood.domain.repository.ProdutoRepository;
import com.joaoandrade.deliveryfood.infrastructure.repository.ProdutoSpecificator;

@Service
public class CrudProdutoService {

	@Autowired
	private ProdutoRepository repository;

	public Page<Produto> buscarTodos(ProdutoFilter produtoFilter, Pageable pageable) {
		return this.repository.findAll(ProdutoSpecificator.buscarProdutos(produtoFilter), pageable);
	}

	public Produto buscarPorId(String id) {
		return this.repository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(
				String.format("O produto de id %s não foi encontrado no sistema!", id)));
	}

	@Transactional
	public Produto cadastrar(Produto produto) {
		return this.repository.save(produto);
	}

	@Transactional
	public void deletarPorId(String id) {
		Produto produto = this.buscarPorId(id);

		try {
			this.repository.deleteById(id);
			this.repository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(
					"Não foi possivel deletar o produto '%s', pois ela estar em uso no sistema", produto.getNome()));
		}
	}

}
