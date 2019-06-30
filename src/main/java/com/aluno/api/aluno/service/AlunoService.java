package com.aluno.api.aluno.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aluno.api.aluno.model.Aluno;

/**
 * @author rapha
 *
 */
@Service
public interface AlunoService {

	List<Aluno> buscarTodos();

	Aluno buscarPeloId(Long idAluno);

	Aluno salvarAluno(Aluno aluno);

	void deletarAluno(Long idAluno);

	Aluno atualizarAluno(Aluno aluno, Long idAluno);
}
