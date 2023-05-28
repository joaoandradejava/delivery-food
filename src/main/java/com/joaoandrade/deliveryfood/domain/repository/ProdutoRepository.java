package com.joaoandrade.deliveryfood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joaoandrade.deliveryfood.domain.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, String> {

}
