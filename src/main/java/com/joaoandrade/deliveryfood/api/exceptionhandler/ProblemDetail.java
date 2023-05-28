package com.joaoandrade.deliveryfood.api.exceptionhandler;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_EMPTY)
public class ProblemDetail {
	private final LocalDateTime timestamp = LocalDateTime.now();
	private final String type;
	private final String title;
	private final int status;
	private final String detail;
	private final String userMessage;

	public ProblemDetail(String type, String title, int status, String detail, String userMessage) {
		super();
		this.type = type;
		this.title = title;
		this.status = status;
		this.detail = detail;
		this.userMessage = userMessage;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public String getType() {
		return type;
	}

	public String getTitle() {
		return title;
	}

	public int getStatus() {
		return status;
	}

	public String getDetail() {
		return detail;
	}

	public String getUserMessage() {
		return userMessage;
	}

	public static ProblemDetail montarProblemDetail(String type, String title, int status, String detail) {
		return montarProblemDetail(type, title, status, detail, detail);
	}

	public static ProblemDetail montarProblemDetail(String type, String title, int status, String detail,
			String userMessage) {
		return new ProblemDetail(type, title, status, detail, userMessage);
	}
}
