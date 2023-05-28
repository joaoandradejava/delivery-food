package com.joaoandrade.deliveryfood.api.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joaoandrade.deliveryfood.api.input.UsuarioCreateInput;
import com.joaoandrade.deliveryfood.domain.model.Usuario;

@Component
public class UsuarioCreateInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Usuario toDomainModel(UsuarioCreateInput usuarioCreateInput) {
		return this.modelMapper.map(usuarioCreateInput, Usuario.class);
	}
}
