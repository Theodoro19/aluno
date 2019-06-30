package com.aluno.api.aluno.handler;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(NOT_FOUND)
@SuppressWarnings("serial")
public class AlunoNotFound extends RuntimeException {

	public AlunoNotFound(String message) {
		super(message);
	}
}
