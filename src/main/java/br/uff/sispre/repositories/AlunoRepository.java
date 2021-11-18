package br.uff.sispre.repositories;

import org.springframework.data.repository.CrudRepository;

import br.uff.sispre.models.Aluno;

public interface AlunoRepository extends CrudRepository<Aluno, Integer> {

}
