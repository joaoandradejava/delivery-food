package com.joaoandrade.deliveryfood.api.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class MudancaSenhaInput {
    @NotBlank
    @Size(min = 6, max = 255)
    private String senhaAtual;
    @NotBlank
    @Size(min = 6, max = 255)
    private String novaSenha;
    @NotBlank
    @Size(min = 6, max = 255)
    private String confirmacaoNovaSenha;

    public MudancaSenhaInput() {
    }

    public String getSenhaAtual() {
        return senhaAtual;
    }

    public void setSenhaAtual(String senhaAtual) {
        this.senhaAtual = senhaAtual;
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }

    public String getConfirmacaoNovaSenha() {
        return confirmacaoNovaSenha;
    }

    public void setConfirmacaoNovaSenha(String confirmacaoNovaSenha) {
        this.confirmacaoNovaSenha = confirmacaoNovaSenha;
    }
}
