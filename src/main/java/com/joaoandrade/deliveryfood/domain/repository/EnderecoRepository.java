package com.joaoandrade.deliveryfood.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.joaoandrade.deliveryfood.domain.model.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

	@Query("select e from Endereco e where e.usuario.id = :usuarioId and e.id = :enderecoId")
	Optional<Endereco> buscarEnderecoDoUsuario(Long usuarioId, Long enderecoId);
}
