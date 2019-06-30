package com.aluno.api.aluno.handler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(BAD_REQUEST)
@SuppressWarnings("serial")
public class AlunoBadRequest extends RuntimeException {

	public AlunoBadRequest(String message) {
		super(message);
	}

}
