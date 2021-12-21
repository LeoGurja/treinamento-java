package br.uff.sispre.dao;

import org.springframework.data.repository.CrudRepository;

import br.uff.sispre.model.Turma;

public interface TurmaDao extends CrudRepository<Turma, Long> {
  Turma findByNome(String nome);
}
