package com.joaoandrade.deliveryfood.api.exceptionhandler;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.joaoandrade.deliveryfood.domain.exception.EntidadeEmUsoException;
import com.joaoandrade.deliveryfood.domain.exception.EntidadeNaoEncontradaException;
import com.joaoandrade.deliveryfood.domain.exception.ErroInternoServidorException;
import com.joaoandrade.deliveryfood.domain.exception.NegocioException;

@ControllerAdvice
public class ResourceHandler extends ResponseEntityExceptionHandler {
	private static final String MENSAGEM_PADRAO_ERROR = "Ocorreu um erro inesperado, se o problema persistir recomendo que entre em contato com o desenvolvedor da API.";

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleErroGenerico(Exception ex, WebRequest request) {
		Error error = Error.ERRO_INTERNO_NO_SERVIDOR;
		String mensagem = "Ocorreu um erro interno no servidor. Sugiro que tente debugar a aplicação para encontrar a causa do erro.";
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		ProblemDetail problemDetail = ProblemDetail.montarProblemDetail(error.getType(), error.getTitle(),
				status.value(), mensagem, MENSAGEM_PADRAO_ERROR);

		return this.handleExceptionInternal(ex, problemDetail, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(ErroInternoServidorException.class)
	public ResponseEntity<Object> handleErroInternoServidor(ErroInternoServidorException ex, WebRequest request) {
		Error error = Error.ERRO_INTERNO_NO_SERVIDOR;
		String mensagem = ex.getMessage();
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		ProblemDetail problemDetail = ProblemDetail.montarProblemDetail(error.getType(), error.getTitle(),
				status.value(), mensagem, MENSAGEM_PADRAO_ERROR);

		return this.handleExceptionInternal(ex, problemDetail, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex, WebRequest request) {
		Throwable cause = ex.getCause();
		if (cause instanceof ConstraintViolationException) {
			return this.handleConstraintViolation((ConstraintViolationException) cause, request);
		}

		Error error = Error.ERRO_INTEGRIDADE_DOS_DADOS;
		String mensagem = "Ocorreu um erro de integridade dos dados.";
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		ProblemDetail problemDetail = ProblemDetail.montarProblemDetail(error.getType(), error.getTitle(),
				status.value(), mensagem, MENSAGEM_PADRAO_ERROR);

		return this.handleExceptionInternal(ex, problemDetail, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<Object> handleNegocio(NegocioException ex, WebRequest request) {
		Error error = Error.NEGOCIO_EXCEPTION;
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String mensagem = ex.getMessage();
		ProblemDetail problemDetail = ProblemDetail.montarProblemDetail(error.getType(), error.getTitle(),
				status.value(), mensagem);

		return this.handleExceptionInternal(ex, problemDetail, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<Object> handleEntidadeNaoEncontrada(EntidadeNaoEncontradaException ex, WebRequest request) {
		Error error = Error.ENTIDADE_NAO_ENCONTRADA;
		HttpStatus status = HttpStatus.NOT_FOUND;
		String mensagem = ex.getMessage();
		ProblemDetail problemDetail = ProblemDetail.montarProblemDetail(error.getType(), error.getTitle(),
				status.value(), mensagem);

		return this.handleExceptionInternal(ex, problemDetail, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<Object> handleEntidadeEmUso(EntidadeEmUsoException ex, WebRequest request) {
		Error error = Error.ENTIDADE_EM_USO;
		HttpStatus status = HttpStatus.CONFLICT;
		String mensagem = ex.getMessage();
		ProblemDetail problemDetail = ProblemDetail.montarProblemDetail(error.getType(), error.getTitle(),
				status.value(), mensagem);

		return this.handleExceptionInternal(ex, problemDetail, new HttpHeaders(), status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		Error error = Error.DADOS_INVALIDOS;
		String mensagem = "Os dados inseridos estão inválidos.";
		ProblemDetail problemDetail = ProblemDetail.montarProblemDetail(error.getType(), error.getTitle(),
				status.value(), mensagem);

		for (ObjectError objectError : ex.getAllErrors()) {
			String field = objectError.getObjectName();

			if (objectError instanceof FieldError) {
				field = ((FieldError) objectError).getField();
			}

			String userMessage = this.messageSource.getMessage(objectError, LocaleContextHolder.getLocale());
			problemDetail.adicionarError(field, userMessage);
		}

		return this.handleExceptionInternal(ex, problemDetail, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		Throwable cause = ex.getCause();
		if (cause instanceof UnrecognizedPropertyException) {
			return this.handleUnrecognizedProperty((UnrecognizedPropertyException) cause, headers, status, request);
		}

		Error error = Error.ERRO_NA_TENTATIVA_DESSERIALIZACAO_JSON;
		String mensagem = "Ocorreu um erro na tentativa de desserialização do JSON. Verfique se os dados foram inseridos corretamente no corpo da requisição.";
		ProblemDetail problemDetail = ProblemDetail.montarProblemDetail(error.getType(), error.getTitle(),
				status.value(), mensagem, MENSAGEM_PADRAO_ERROR);

		return this.handleExceptionInternal(ex, problemDetail, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatusCode status, WebRequest request) {
		Error error = Error.ENDPOINT_NAO_ENCONTRADO;
		String mensagem = String.format("Não foi encontrado o endpoint para '%s%s'", ex.getHttpMethod(),
				ex.getRequestURL());
		ProblemDetail problemDetail = ProblemDetail.montarProblemDetail(error.getType(), error.getTitle(),
				status.value(), mensagem, MENSAGEM_PADRAO_ERROR);

		return this.handleExceptionInternal(ex, problemDetail, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		Error error = Error.METODO_REQUISICAO_NAO_SUPORTADO;
		String mensagem = String.format("A requisição feita para o metodo %s não é suportada", ex.getMethod());
		ProblemDetail problemDetail = ProblemDetail.montarProblemDetail(error.getType(), error.getTitle(),
				status.value(), mensagem, MENSAGEM_PADRAO_ERROR);

		return this.handleExceptionInternal(ex, problemDetail, headers, status, request);
	}

	private ResponseEntity<Object> handleUnrecognizedProperty(UnrecognizedPropertyException ex, HttpHeaders headers,
			HttpStatusCode status, WebRequest request) {
		Error error = Error.PROPRIEDADE_INEXISTENTE;
		String mensagem = String.format("A propriedade '%s' é inexistente!", ex.getPropertyName());
		ProblemDetail problemDetail = ProblemDetail.montarProblemDetail(error.getType(), error.getTitle(),
				status.value(), mensagem, MENSAGEM_PADRAO_ERROR);

		return this.handleExceptionInternal(ex, problemDetail, headers, status, request);
	}

	private ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
		Error error = Error.ERRO_CONSTRAINT;
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		String mensagem = ConstraintViolation.getConstraintViolation(ex.getConstraintName()).getMensagem();
		ProblemDetail problemDetail = ProblemDetail.montarProblemDetail(error.getType(), error.getTitle(),
				status.value(), mensagem);

		return this.handleExceptionInternal(ex, problemDetail, new HttpHeaders(), status, request);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatusCode statusCode, WebRequest request) {

		HttpStatus status = HttpStatus.valueOf(statusCode.value());
		if (body == null) {
			body = ProblemDetail.montarProblemDetail(null, status.getReasonPhrase(), statusCode.value(),
					MENSAGEM_PADRAO_ERROR);
		}

		return super.handleExceptionInternal(ex, body, headers, statusCode, request);
	}
}
