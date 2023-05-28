package com.joaoandrade.deliveryfood.api.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joaoandrade.deliveryfood.api.input.CategoriaInput;
import com.joaoandrade.deliveryfood.domain.model.Categoria;

@Component
public class CategoriaInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Categoria toDomainModel(CategoriaInput categoriaInput) {
		return this.modelMapper.map(categoriaInput, Categoria.class);
	}

	public void copyToDomainModel(CategoriaInput categoriaInput, Categoria categoria) {
		this.modelMapper.map(categoriaInput, categoria);
	}
}
