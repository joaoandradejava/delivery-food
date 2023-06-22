package com.joaoandrade.deliveryfood.api.controller;

import com.joaoandrade.deliveryfood.api.input.MudancaSenhaInput;
import com.joaoandrade.deliveryfood.core.security.UsuarioAutenticado;
import com.joaoandrade.deliveryfood.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.joaoandrade.deliveryfood.api.assembler.UsuarioFullModelAssembler;
import com.joaoandrade.deliveryfood.api.disassembler.UsuarioCreateInputDisassembler;
import com.joaoandrade.deliveryfood.api.disassembler.UsuarioUpdateInputDisassembler;
import com.joaoandrade.deliveryfood.api.input.UsuarioCreateInput;
import com.joaoandrade.deliveryfood.api.input.UsuarioUpdateInput;
import com.joaoandrade.deliveryfood.api.model.UsuarioFullModel;
import com.joaoandrade.deliveryfood.domain.model.Usuario;
import com.joaoandrade.deliveryfood.domain.service.crud.CrudUsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioCreateInputDisassembler usuarioCreateInputDisassembler;

    @Autowired
    private CrudUsuarioService crudUsuarioService;

    @Autowired
    private UsuarioFullModelAssembler usuarioFullModelAssembler;

    @Autowired
    private UsuarioUpdateInputDisassembler usuarioUpdateInputDisassembler;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/{id}")
    public UsuarioFullModel buscarPorId(@PathVariable Long id) {
        Usuario usuario = this.crudUsuarioService.buscarPorId(id);

        return this.usuarioFullModelAssembler.toModel(usuario);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public UsuarioFullModel cadastrar(@Valid @RequestBody UsuarioCreateInput usuarioCreateInput) {
        Usuario usuario = this.crudUsuarioService
                .cadastrar(this.usuarioCreateInputDisassembler.toDomainModel(usuarioCreateInput));

        return this.usuarioFullModelAssembler.toModel(usuario);
    }

    @PutMapping("/{id}")
    public UsuarioFullModel atualizar(@PathVariable Long id,
                                      @Valid @RequestBody UsuarioUpdateInput usuarioUpdateInput) {
        Usuario usuario = this.crudUsuarioService.buscarPorId(id);
        this.usuarioUpdateInputDisassembler.copyToDomainModel(usuarioUpdateInput, usuario);
        usuario = this.crudUsuarioService.atualizar(usuario);

        return this.usuarioFullModelAssembler.toModel(usuario);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deletarPorId(@PathVariable Long id) {
        this.crudUsuarioService.deletarPorId(id);
    }

    @PutMapping("/senha")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void mudarSenha(@Valid @RequestBody MudancaSenhaInput mudancaSenhaInput, @AuthenticationPrincipal UsuarioAutenticado usuarioAutenticado) {
        this.usuarioService.mudarSenha(mudancaSenhaInput.getSenhaAtual(), mudancaSenhaInput.getNovaSenha(), mudancaSenhaInput.getConfirmacaoNovaSenha(), usuarioAutenticado.getId());
    }
}
