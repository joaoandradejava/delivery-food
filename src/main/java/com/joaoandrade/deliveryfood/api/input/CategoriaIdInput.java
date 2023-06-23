package com.joaoandrade.deliveryfood.api.input;

import jakarta.validation.constraints.NotNull;

public class CategoriaIdInput {

    @NotNull
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

