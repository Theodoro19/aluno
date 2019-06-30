package com.aluno.api.aluno.handler;

import static org.springframework.http.HttpStatus.CONFLICT;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(CONFLICT)
@SuppressWarnings("serial")
public class AlunoConflict extends RuntimeException {

	public AlunoConflict(String message) {
		super(message);
	}
}
