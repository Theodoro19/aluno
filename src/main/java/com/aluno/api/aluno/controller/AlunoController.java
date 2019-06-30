package com.aluno.api.aluno.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aluno.api.aluno.model.Aluno;
import com.aluno.api.aluno.service.AlunoService;

@RestController
@RequestMapping(value = "/aluno")
public class AlunoController {

	@Autowired
	private AlunoService alunoService;

	@RequestMapping(method = GET)
	public ResponseEntity<List<Aluno>> buscarTodos() {

		return new ResponseEntity<List<Aluno>>(alunoService.buscarTodos(), OK);
	}

	@RequestMapping(value = "/{idAluno}", method = GET)
	public ResponseEntity<Aluno> buscarPeloId(@PathVariable(value = "idAluno") Long idAluno) {

		return new ResponseEntity<Aluno>(alunoService.buscarPeloId(idAluno), OK);
	}

	@RequestMapping(method = POST)
	public ResponseEntity<Aluno> salvar(@RequestBody @Valid Aluno aluno) {

		return new ResponseEntity<Aluno>(alunoService.salvarAluno(aluno), CREATED);
	}

	@RequestMapping(value = "/{idAluno}", method = PUT)
	public ResponseEntity<Aluno> update(@PathVariable(value = "idAluno") Long idAluno,
			@Valid @RequestBody Aluno aluno) {

		return new ResponseEntity<Aluno>(alunoService.atualizarAluno(aluno, idAluno), OK);
	}

	@RequestMapping(value = "/{idAluno}", method = DELETE)
	public ResponseEntity<String> delete(@PathVariable Long idAluno) {

		alunoService.deletarAluno(idAluno);

		return new ResponseEntity<String>("Deletado com sucesso!", OK);
	}
}
