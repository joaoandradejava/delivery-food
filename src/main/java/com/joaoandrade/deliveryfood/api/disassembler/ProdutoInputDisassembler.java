package com.joaoandrade.deliveryfood.api.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joaoandrade.deliveryfood.api.input.ProdutoInput;
import com.joaoandrade.deliveryfood.domain.model.Produto;

@Component
public class ProdutoInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Produto toDomainModel(ProdutoInput produtoInput) {
		return this.modelMapper.map(produtoInput, Produto.class);
	}

	public void copyToDomainModel(ProdutoInput produtoInput, Produto produto) {
		this.modelMapper.map(produtoInput, produto);
	}
}
