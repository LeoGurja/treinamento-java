package br.uff.sispre.repositories;

import org.springframework.data.repository.CrudRepository;

import br.uff.sispre.models.Professor;

public interface ProfessorRepository extends CrudRepository<Professor, Long> {

}
