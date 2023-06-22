package com.joaoandrade.deliveryfood.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.joaoandrade.deliveryfood.api.assembler.ProdutoFullModelAssembler;
import com.joaoandrade.deliveryfood.api.assembler.ProdutoModelAssembler;
import com.joaoandrade.deliveryfood.api.disassembler.ProdutoInputDisassembler;
import com.joaoandrade.deliveryfood.api.input.ProdutoInput;
import com.joaoandrade.deliveryfood.api.model.ProdutoFullModel;
import com.joaoandrade.deliveryfood.api.model.ProdutoModel;
import com.joaoandrade.deliveryfood.domain.filter.ProdutoFilter;
import com.joaoandrade.deliveryfood.domain.model.Produto;
import com.joaoandrade.deliveryfood.domain.service.crud.CrudProdutoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private CrudProdutoService crudProdutoService;

	@Autowired
	private ProdutoFullModelAssembler produtoFullModelAssembler;

	@Autowired
	private ProdutoInputDisassembler produtoInputDisassembler;

	@Autowired
	private ProdutoModelAssembler produtoModelAssembler;

	@GetMapping
	public Page<ProdutoModel> buscarTodos(Pageable pageable, ProdutoFilter produtoFilter) {
		Page<Produto> page = this.crudProdutoService.buscarTodos(produtoFilter, pageable);

		return page.map(p -> this.produtoModelAssembler.toModel(p));
	}

	@GetMapping("/{id}")
	public ProdutoFullModel buscarPorId(@PathVariable String id) {
		Produto produto = this.crudProdutoService.buscarPorId(id);

		return this.produtoFullModelAssembler.toModel(produto);
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public ProdutoFullModel cadastrar(@Valid @RequestBody ProdutoInput produtoInputD) {
		Produto produto = this.crudProdutoService.cadastrar(this.produtoInputDisassembler.toDomainModel(produtoInputD));

		return this.produtoFullModelAssembler.toModel(produto);
	}

	@PutMapping("/{id}")
	public ProdutoFullModel atualizar(@PathVariable String id, @Valid @RequestBody ProdutoInput produtoInput) {
		Produto produto = this.crudProdutoService.buscarPorId(id);
		this.produtoInputDisassembler.copyToDomainModel(produtoInput, produto);
		produto = this.crudProdutoService.atualizar(produto);

		return this.produtoFullModelAssembler.toModel(produto);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deletarPorId(@PathVariable String id) {
		this.crudProdutoService.deletarPorId(id);
	}
}
