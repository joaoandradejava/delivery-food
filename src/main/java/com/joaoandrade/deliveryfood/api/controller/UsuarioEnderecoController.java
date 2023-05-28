package com.joaoandrade.deliveryfood.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.joaoandrade.deliveryfood.api.assembler.EnderecoModelAssembler;
import com.joaoandrade.deliveryfood.api.disassembler.EnderecoInputDisassembler;
import com.joaoandrade.deliveryfood.api.input.EnderecoInput;
import com.joaoandrade.deliveryfood.api.model.EnderecoModel;
import com.joaoandrade.deliveryfood.domain.model.Endereco;
import com.joaoandrade.deliveryfood.domain.service.crud.CrudUsuarioEnderecoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios/{usuarioId}/enderecos")
public class UsuarioEnderecoController {

	@Autowired
	private CrudUsuarioEnderecoService crudUsuarioEnderecoService;

	@Autowired
	private EnderecoInputDisassembler enderecoInputDisassembler;

	@Autowired
	private EnderecoModelAssembler enderecoModelAssembler;

	@GetMapping("/{enderecoId}")
	public EnderecoModel buscarEndereco(@PathVariable Long usuarioId, @PathVariable Long enderecoId) {
		Endereco endereco = this.crudUsuarioEnderecoService.buscarEndereco(usuarioId, enderecoId);

		return this.enderecoModelAssembler.toModel(endereco);
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public EnderecoModel adicionarEndereco(@PathVariable Long usuarioId,
			@Valid @RequestBody EnderecoInput enderecoInput) {
		Endereco endereco = this.crudUsuarioEnderecoService.adicionarEndereco(usuarioId,
				this.enderecoInputDisassembler.toDomainModel(enderecoInput));

		return this.enderecoModelAssembler.toModel(endereco);
	}

	@PutMapping("/{enderecoId}")
	public EnderecoModel atualizarEndereco(@PathVariable Long usuarioId, @PathVariable Long enderecoId,
			@Valid @RequestBody EnderecoInput enderecoInput) {
		Endereco endereco = this.crudUsuarioEnderecoService.buscarEndereco(usuarioId, enderecoId);
		this.enderecoInputDisassembler.copyToDomainModel(enderecoInput, endereco);
		endereco = this.crudUsuarioEnderecoService.atualizar(endereco);

		return this.enderecoModelAssembler.toModel(endereco);
	}

	@DeleteMapping("/{enderecoId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deletarEnderecoDoUsuario(@PathVariable Long usuarioId, @PathVariable Long enderecoId) {
		this.crudUsuarioEnderecoService.deletarEnderecoDoUsuario(usuarioId, enderecoId);
	}

}
