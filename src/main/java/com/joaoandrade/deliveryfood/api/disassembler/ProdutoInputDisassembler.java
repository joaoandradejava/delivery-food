package com.joaoandrade.deliveryfood.api.disassembler;

import com.joaoandrade.deliveryfood.domain.model.Categoria;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joaoandrade.deliveryfood.api.input.ProdutoInput;
import com.joaoandrade.deliveryfood.domain.model.Produto;

import java.util.HashSet;

@Component
public class ProdutoInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Produto toDomainModel(ProdutoInput produtoInput) {
        Produto produto = this.modelMapper.map(produtoInput, Produto.class);
        this.copyCategoriasIdToDomainModel(produtoInput, produto);

        return produto;
    }


    public void copyToDomainModel(ProdutoInput produtoInput, Produto produto) {
        this.modelMapper.map(produtoInput, produto);

        produto.setCategorias(new HashSet<>());
        this.copyCategoriasIdToDomainModel(produtoInput, produto);
    }

    private void copyCategoriasIdToDomainModel(ProdutoInput produtoInput, Produto produto) {
        produtoInput.getCategoriasId().forEach(categoriaId -> produto.getCategorias().add(new Categoria(categoriaId)));
    }
}
