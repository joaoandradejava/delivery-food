package com.joaoandrade.deliveryfood.infrastructure.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.joaoandrade.deliveryfood.domain.filter.ProdutoFilter;
import com.joaoandrade.deliveryfood.domain.model.Produto;

import jakarta.persistence.criteria.Predicate;

public class ProdutoSpecificator {
	public static Specification<Produto> buscarProdutos(ProdutoFilter produtoFilter) {
		return (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();

			if (StringUtils.hasLength(produtoFilter.getNome())) {
				Predicate predicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("nome")),
						"%" + produtoFilter.getNome().toLowerCase() + "%");
				predicates.add(predicate);
			}

			if (produtoFilter.getCategoriaIds().size() > 0) {
				Predicate predicate = root.join("categorias").get("id").in(produtoFilter.getCategoriaIds());

				predicates.add(predicate);
			}

			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

		};
	}
}
