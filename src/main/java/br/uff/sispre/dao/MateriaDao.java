package br.uff.sispre.dao;

import org.springframework.data.repository.CrudRepository;

import br.uff.sispre.model.Materia;

public interface MateriaDao extends CrudRepository<Materia, Long> {
  Materia findByName(String name);
}
