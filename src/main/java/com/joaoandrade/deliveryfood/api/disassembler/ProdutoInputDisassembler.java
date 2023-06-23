package com.joaoandrade.deliveryfood.api.disassembler;

import com.joaoandrade.deliveryfood.api.input.ProdutoInput;
import com.joaoandrade.deliveryfood.domain.model.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class ProdutoInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Produto toDomainModel(ProdutoInput produtoInput) {
        return this.modelMapper.map(produtoInput, Produto.class);

    }
    
    public void copyToDomainModel(ProdutoInput produtoInput, Produto produto) {
        produto.setCategorias(new HashSet<>());
        this.modelMapper.map(produtoInput, produto);
    }


}
