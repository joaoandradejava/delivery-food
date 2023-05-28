package com.joaoandrade.deliveryfood.api.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joaoandrade.deliveryfood.api.input.EnderecoInput;
import com.joaoandrade.deliveryfood.domain.model.Endereco;

@Component
public class EnderecoInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Endereco toDomainModel(EnderecoInput enderecoInput) {
		return this.modelMapper.map(enderecoInput, Endereco.class);
	}

	public void copyToDomainModel(EnderecoInput enderecoInput, Endereco endereco) {
		this.modelMapper.map(enderecoInput, endereco);
	}
}
