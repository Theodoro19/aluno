package com.aluno.api.aluno.model;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * @author rapha
 *
 */
public interface AlunoRepository extends CrudRepository<Aluno, Long>, JpaSpecificationExecutor<Aluno> {

}
