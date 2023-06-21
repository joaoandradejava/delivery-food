package com.joaoandrade.deliveryfood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import com.joaoandrade.deliveryfood.api.assembler.CategoriaModelAssembler;
import com.joaoandrade.deliveryfood.api.disassembler.CategoriaInputDisassembler;
import com.joaoandrade.deliveryfood.api.input.CategoriaInput;
import com.joaoandrade.deliveryfood.api.model.CategoriaModel;
import com.joaoandrade.deliveryfood.domain.model.Categoria;
import com.joaoandrade.deliveryfood.domain.service.crud.CrudCategoriaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CrudCategoriaService crudCategoriaService;

    @Autowired
    private CategoriaModelAssembler categoriaModelAssembler;

    @Autowired
    private CategoriaInputDisassembler categoriaInputDisassembler;

    @GetMapping
    public Page<CategoriaModel> buscarTodos(Pageable pageable) {
        Page<Categoria> page = this.crudCategoriaService.buscarTodos(pageable);

        return page.map(x -> this.categoriaModelAssembler.toModel(x));
    }

    @GetMapping("/{id}")
    public CategoriaModel buscarPorId(@PathVariable Long id) {
        Categoria categoria = this.crudCategoriaService.buscarPorId(id);

        return this.categoriaModelAssembler.toModel(categoria);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public CategoriaModel cadastrar(@Valid @RequestBody CategoriaInput categoriaInput) {
        Categoria categoria = this.crudCategoriaService
                .cadastrar(this.categoriaInputDisassembler.toDomainModel(categoriaInput));

        return this.categoriaModelAssembler.toModel(categoria);
    }

    @PutMapping("/{id}")
    public CategoriaModel atualizar(@PathVariable Long id, @Valid @RequestBody CategoriaInput categoriaInput) {
        Categoria categoria = this.crudCategoriaService.buscarPorId(id);
        this.categoriaInputDisassembler.copyToDomainModel(categoriaInput, categoria);
        categoria = this.crudCategoriaService.atualizar(categoria);

        return this.categoriaModelAssembler.toModel(categoria);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deletarPorId(@PathVariable Long id) {
        this.crudCategoriaService.deletarPorId(id);
    }

}
