package com.joaoandrade.deliveryfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joaoandrade.deliveryfood.api.model.UsuarioFullModel;
import com.joaoandrade.deliveryfood.domain.model.Usuario;

@Component
public class UsuarioFullModelAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public UsuarioFullModel toModel(Usuario usuario) {
		return this.modelMapper.map(usuario, UsuarioFullModel.class);
	}
}
