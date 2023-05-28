package com.joaoandrade.deliveryfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joaoandrade.deliveryfood.api.model.ProdutoFullModel;
import com.joaoandrade.deliveryfood.domain.model.Produto;

@Component
public class ProdutoFullModelAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public ProdutoFullModel toModel(Produto produto) {
		return this.modelMapper.map(produto, ProdutoFullModel.class);
	}
}
