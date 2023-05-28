package com.joaoandrade.deliveryfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joaoandrade.deliveryfood.api.model.EnderecoModel;
import com.joaoandrade.deliveryfood.domain.model.Endereco;

@Component
public class EnderecoModelAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public EnderecoModel toModel(Endereco endereco) {
		return this.modelMapper.map(endereco, EnderecoModel.class);
	}
}
