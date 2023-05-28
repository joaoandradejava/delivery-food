package com.joaoandrade.deliveryfood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joaoandrade.deliveryfood.api.model.CategoriaModel;
import com.joaoandrade.deliveryfood.domain.model.Categoria;

@Component
public class CategoriaModelAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public CategoriaModel toModel(Categoria categoria) {
		return this.modelMapper.map(categoria, CategoriaModel.class);
	}

	public List<CategoriaModel> toCollectionModel(List<Categoria> lista) {
		return lista.stream().map(x -> this.toModel(x)).collect(Collectors.toList());
	}

}
