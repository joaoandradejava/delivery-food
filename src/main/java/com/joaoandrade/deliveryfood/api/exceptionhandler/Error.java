package com.joaoandrade.deliveryfood.api.exceptionhandler;

public enum Error {
	NEGOCIO_EXCEPTION("negocio-exception", "Ocorreu um erro do lado do client-side(front-end)"),
	ENTIDADE_NAO_ENCONTRADA("entidade-nao-encontrada", "Entidade não encontrada"),
	ENTIDADE_EM_USO("entidade-em-uso", "Entidade em uso"),
	ERRO_NA_TENTATIVA_DESSERIALIZACAO_JSON("erro-tentativa-desserializacao-json",
			"Ocorreu um erro na tentativa de desserialização do JSON"),
	PROPRIEDADE_INEXISTENTE("propriedade-inexistente", "Propriedade inexistente"),
	ENDPOINT_NAO_ENCONTRADO("endpoint-nao-encontrado", "Endpoint não encontrado"),
	METODO_REQUISICAO_NAO_SUPORTADO("metodo-requisicao-nao-suportado", "Metodo de requisição não suportado"),
	ERRO_INTERNO_NO_SERVIDOR("erro-interno-no-servidor", "Erro interno no servidor");

	private final String type;
	private final String title;

	private Error(String type, String title) {
		this.type = "https://www.joaoandradejava.com.br/" + type;
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public String getTitle() {
		return title;
	}

}
