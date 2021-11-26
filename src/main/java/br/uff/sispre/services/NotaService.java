package br.uff.sispre.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.uff.sispre.controllers.resources.NotaResource;
import br.uff.sispre.models.Nota;
import br.uff.sispre.repositories.AlunoRepository;
import br.uff.sispre.repositories.MateriaRepository;
import br.uff.sispre.repositories.NotaRepository;

@Service
public class NotaService {
  @Autowired
  private NotaRepository repo;

  @Autowired
  private AlunoRepository alunoRepo;

  @Autowired
  private MateriaRepository materiaRepo;

  private Nota nota;

  public Nota find(Long id) {
    return repo.findById(id).get();
  }

  public Nota create(NotaResource params) {
    nota = new Nota();
    apply(params);
    return repo.save(nota);
  }

  public Nota update(Long id, NotaResource params) {
    nota = repo.findById(id).get();
    apply(params);
    return repo.save(nota);
  }

  public void delete(Long id) {
    repo.deleteById(id);
  }

  public List<Nota> all() {
    return (List<Nota>) repo.findAll();
  }

  private void apply(NotaResource params) {
    nota.setValue(params.value);
    nota.setAluno(alunoRepo.findById(params.alunoId).get());
    nota.setMateria(materiaRepo.findById(params.materiaId).get());
  }
}
