package com.joaoandrade.deliveryfood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joaoandrade.deliveryfood.domain.model.Categoria;
import com.joaoandrade.deliveryfood.domain.model.Produto;
import com.joaoandrade.deliveryfood.domain.service.crud.CrudCategoriaService;
import com.joaoandrade.deliveryfood.domain.service.crud.CrudProdutoService;

@Service
public class ProdutoCategoriaService {

	@Autowired
	private CrudCategoriaService crudCategoriaService;

	@Autowired
	private CrudProdutoService crudProdutoService;

	@Transactional
	public void associarCategoria(String produtoId, Long categoriaId) {
		Produto produto = this.crudProdutoService.buscarPorId(produtoId);
		Categoria categoria = this.crudCategoriaService.buscarPorId(categoriaId);

		produto.associarCategoria(categoria);
	}

	@Transactional
	public void dissociarCategoria(String produtoId, Long categoriaId) {
		Produto produto = this.crudProdutoService.buscarPorId(produtoId);
		Categoria categoria = this.crudCategoriaService.buscarPorId(categoriaId);

		produto.dissociarCategoria(categoria);
	}

}
