package com.joaoandrade.deliveryfood.domain.service.crud;

import com.joaoandrade.deliveryfood.domain.exception.CategoriaNaoEncontradaException;
import com.joaoandrade.deliveryfood.domain.exception.EntidadeEmUsoException;
import com.joaoandrade.deliveryfood.domain.model.Categoria;
import com.joaoandrade.deliveryfood.domain.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CrudCategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public Page<Categoria> buscarTodos(Pageable pageable) {
        return this.repository.findAll(pageable);
    }

    public Categoria buscarPorId(Long id) {
        return this.repository.findById(id).orElseThrow(() -> new CategoriaNaoEncontradaException(
                String.format("A Categoria de id %d não foi encontrada no sistema!", id)));
    }

    @Transactional
    public Categoria cadastrar(Categoria categoria) {
        return this.repository.save(categoria);
    }

    @Transactional
    public Categoria atualizar(Categoria categoria) {
        return this.repository.save(categoria);
    }

    @Transactional
    public void deletarPorId(Long id) {
        Categoria categoria = this.buscarPorId(id);

        try {
            this.repository.deleteById(id);
            this.repository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(
                    "Não foi possivel deletar a categoria %s, pois ela estar em uso no sistema!", categoria.getNome()));
        }

    }
}
