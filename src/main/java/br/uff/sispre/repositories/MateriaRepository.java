package br.uff.sispre.repositories;

import br.uff.sispre.models.Materia;

public interface MateriaRepository extends ModelRepository<Materia> {
  Materia findByName(String name);
}
