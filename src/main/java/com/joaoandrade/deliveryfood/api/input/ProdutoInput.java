package com.joaoandrade.deliveryfood.api.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class ProdutoInput {

    @NotBlank
    @Size(max = 255)
    private String nome;
    private String descricao;

    @NotNull
    private BigDecimal preco;
    private String fotoUrl;

    @Valid
    @NotEmpty
    private Set<CategoriaIdInput> categorias = new HashSet<>();

    public ProdutoInput() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    public Set<CategoriaIdInput> getCategorias() {
        return categorias;
    }

    public void setCategorias(Set<CategoriaIdInput> categorias) {
        this.categorias = categorias;
    }
}
