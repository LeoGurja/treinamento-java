package br.uff.sispre.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.uff.sispre.models.Nota;
import br.uff.sispre.repositories.NotaRepository;

@Service
public class NotaService {
  @Autowired
  private NotaRepository repo;

  public Nota find(Long id) {
    return repo.findById(id).get();
  }

  public void create(Nota nota) {
    repo.save(nota);
  }

  public void update(Long id, Nota nota) {
    repo.save(nota);
  }

  public void delete(Long id) {
    repo.deleteById(id);
  }

  public Iterable<Nota> all() {
    return repo.findAll();
  }
}
