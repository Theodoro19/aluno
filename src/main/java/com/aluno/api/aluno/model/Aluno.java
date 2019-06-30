package com.aluno.api.aluno.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

import lombok.Data;

/**
 * @author rapha
 *
 */
@Data
@Entity
public class Aluno {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@NotBlank(message = "É necessário informar o nome do Aluno.")
	private String nomeAluno;

	@PositiveOrZero(message = "É necessário informar uma idade válida.")
	private Integer idadeAluno;
}
