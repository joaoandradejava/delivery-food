package com.joaoandrade.deliveryfood.domain.exception;

public class ErroInternoServidorException extends NegocioException {

	private static final long serialVersionUID = 1L;

	public ErroInternoServidorException(String mensagem) {
		super(mensagem);
	}

}
