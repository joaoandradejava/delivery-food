package com.joaoandrade.deliveryfood.domain.service;


import com.joaoandrade.deliveryfood.domain.exception.NegocioException;
import com.joaoandrade.deliveryfood.domain.model.Usuario;
import com.joaoandrade.deliveryfood.domain.service.crud.CrudUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    @Autowired
    private CrudUsuarioService crudUsuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void mudarSenha(String senhaAtual, String novaSenha, String confirmacaoNovaSenha, Long id) {
        Usuario usuario = this.crudUsuarioService.buscarPorId(id);

        if (!this.passwordEncoder.matches(senhaAtual, usuario.getSenha())) {
            throw new NegocioException("A senha atual informada não corresponde a sua senha!");
        }

        if (!novaSenha.equals(confirmacaoNovaSenha)) {
            throw new NegocioException("A confirmação da nova senha está diferente da nova senha informada");
        }

        usuario.setSenha(this.passwordEncoder.encode(novaSenha));

    }
}
