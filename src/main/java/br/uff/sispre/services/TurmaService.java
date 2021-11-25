package br.uff.sispre.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.uff.sispre.models.Turma;
import br.uff.sispre.repositories.TurmaRepository;

@Service
public class TurmaService {
  @Autowired
  private TurmaRepository repo;

  public Turma find(Long id) {
    return repo.findById(id).get();
  }

  public void create(Turma turma) {
    repo.save(turma);
  }

  public void update(Long id, Turma turma) {
    repo.save(turma);
  }

  public void delete(Long id) {
    repo.deleteById(id);
  }

  public Iterable<Turma> all() {
    return repo.findAll();
  }
}
