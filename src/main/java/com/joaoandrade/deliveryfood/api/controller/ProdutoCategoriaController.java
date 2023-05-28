package com.joaoandrade.deliveryfood.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.joaoandrade.deliveryfood.domain.service.ProdutoCategoriaService;

@RestController
@RequestMapping("/produtos/{produtoId}/categorias/{categoriaId}")
public class ProdutoCategoriaController {

	@Autowired
	private ProdutoCategoriaService produtoCategoriaService;

	@PutMapping
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void associarCategoria(@PathVariable String produtoId, @PathVariable Long categoriaId) {
		this.produtoCategoriaService.associarCategoria(produtoId, categoriaId);
	}

	@DeleteMapping
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void dissociarCategoria(@PathVariable String produtoId, @PathVariable Long categoriaId) {
		this.produtoCategoriaService.dissociarCategoria(produtoId, categoriaId);
	}
}
