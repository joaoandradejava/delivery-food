package com.joaoandrade.deliveryfood.api.exceptionhandler;

import com.joaoandrade.deliveryfood.domain.exception.ErroInternoServidorException;

public enum ConstraintViolation {
	UNIQUE_EMAIL("Já existe um usuario com esté email.");

	private final String mensagem;

	private ConstraintViolation(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

	public static ConstraintViolation getConstraintViolation(String constraintName) {
		for (ConstraintViolation constraintViolation : ConstraintViolation.values()) {
			if (constraintViolation.toString().equalsIgnoreCase(constraintName)) {
				return constraintViolation;
			}
		}

		throw new ErroInternoServidorException("Não foi encontrado a constraint " + constraintName);
	}

}
