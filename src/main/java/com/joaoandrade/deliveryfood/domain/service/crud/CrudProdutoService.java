package com.joaoandrade.deliveryfood.domain.service.crud;

import com.joaoandrade.deliveryfood.domain.exception.EntidadeEmUsoException;
import com.joaoandrade.deliveryfood.domain.exception.EntidadeNaoEncontradaException;
import com.joaoandrade.deliveryfood.domain.filter.ProdutoFilter;
import com.joaoandrade.deliveryfood.domain.model.Categoria;
import com.joaoandrade.deliveryfood.domain.model.Produto;
import com.joaoandrade.deliveryfood.domain.repository.ProdutoRepository;
import com.joaoandrade.deliveryfood.infrastructure.repository.ProdutoSpecificator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class CrudProdutoService {

    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private CrudCategoriaService crudCategoriaService;

    public Page<Produto> buscarTodos(ProdutoFilter produtoFilter, Pageable pageable) {
        return this.repository.findAll(ProdutoSpecificator.buscarProdutos(produtoFilter), pageable);
    }

    public Produto buscarPorId(String id) {
        return this.repository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(
                String.format("O produto de id %s não foi encontrado no sistema!", id)));
    }

    @Transactional
    public Produto cadastrar(Produto produto) {
        this.buscarCategoriasDoProduto(produto);

        return this.repository.save(produto);
    }

    private void buscarCategoriasDoProduto(Produto produto) {
        Set<Categoria> categorias = new HashSet<>();
        produto.getCategorias().forEach(c -> {
            Categoria categoria = this.crudCategoriaService.buscarPorId(c.getId());
            categorias.add(categoria);
        });

        produto.setCategorias(categorias);
    }

    @Transactional
    public Produto atualizar(Produto produto) {
        this.buscarCategoriasDoProduto(produto);

        return this.repository.save(produto);
    }

    @Transactional
    public void deletarPorId(String id) {
        Produto produto = this.buscarPorId(id);

        try {
            this.repository.deleteById(id);
            this.repository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(
                    "Não foi possivel deletar o produto '%s', pois ela estar em uso no sistema", produto.getNome()));
        }
    }

}
