package com.aluno.api.aluno.handler;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionControllerHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(AlunoNotFound.class)
	public void classeNaoEncontrada(HttpServletResponse response) {

		try {
			response.sendError(NOT_FOUND.value());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@ExceptionHandler(AlunoBadRequest.class)
	public void classeMalFormatada(HttpServletResponse response) {

		try {
			response.sendError(BAD_REQUEST.value());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@ExceptionHandler(AlunoConflict.class)
	public void classeConflitante(HttpServletResponse response) {

		try {
			response.sendError(CONFLICT.value());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
