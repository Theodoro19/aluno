package com.aluno.api.aluno.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.aluno.api.aluno.model.Aluno;
import com.aluno.api.aluno.model.AlunoRepository;

/**
 * @author rapha
 *
 */
@Service
public class AlunoServiceImpl implements AlunoService {

	@Autowired
	private AlunoRepository alunoRepository;

	@Override
	public List<Aluno> buscarTodos() {

		Specification<Aluno> spec = null;

		return alunoRepository.findAll(spec);
	}

	@Override
	public Aluno buscarPeloId(Long idAluno) {

		return alunoRepository.findById(idAluno).orElseThrow(
				() -> new EntityNotFoundException("Não foi possível encontrar um aluno com o id informado."));
	}

	@Override
	public Aluno salvarAluno(Aluno aluno) {

		alunoRepository.save(aluno);

		return aluno;
	}

	@Override
	public void deletarAluno(Long idAluno) {

		Aluno aluno = this.buscarPeloId(idAluno);

		if (aluno != null) {

			alunoRepository.delete(aluno);
		}

	}

	@Override
	public Aluno atualizarAluno(Aluno aluno, Long idAluno) {

		Aluno alunoOld = this.buscarPeloId(idAluno);

		if (alunoOld != null) {

			alunoRepository.save(aluno);
		}

		return aluno;
	}

}
