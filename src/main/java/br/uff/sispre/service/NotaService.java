package br.uff.sispre.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.uff.sispre.controller.dto.NotaDto;
import br.uff.sispre.dao.AlunoDao;
import br.uff.sispre.dao.MateriaDao;
import br.uff.sispre.dao.NotaDao;
import br.uff.sispre.model.Nota;

@Service
public class NotaService {
  @Autowired
  private NotaDao repo;

  @Autowired
  private AlunoDao alunoRepo;

  @Autowired
  private MateriaDao materiaRepo;

  private Nota nota;

  public Nota find(Long id) {
    return repo.findById(id).get();
  }

  public Nota create(NotaDto params) {
    nota = new Nota();
    apply(params);
    return repo.save(nota);
  }

  public Nota update(Long id, NotaDto params) {
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

  private void apply(NotaDto params) {
    nota.setValue(params.value);
    nota.setAluno(alunoRepo.findById(params.alunoId).get());
    nota.setMateria(materiaRepo.findById(params.materiaId).get());
  }
}
