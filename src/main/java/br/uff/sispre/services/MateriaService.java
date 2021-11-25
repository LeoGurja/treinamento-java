package br.uff.sispre.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.uff.sispre.models.Materia;
import br.uff.sispre.repositories.MateriaRepository;

@Service
public class MateriaService {
  @Autowired
  private MateriaRepository repo;

  public Materia find(Long id) {
    return repo.findById(id).get();
  }

  public void create(Materia materia) {
    repo.save(materia);
  }

  public void update(Long id, Materia materia) {
    repo.save(materia);
  }

  public void delete(Long id) {
    repo.deleteById(id);
  }

  public Iterable<Materia> all() {
    return repo.findAll();
  }
}
