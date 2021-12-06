package br.uff.sispre.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.uff.sispre.controller.dto.TurmaDto;
import br.uff.sispre.dao.AlunoDao;
import br.uff.sispre.dao.MateriaDao;
import br.uff.sispre.dao.TurmaDao;
import br.uff.sispre.model.Turma;

@Service
public class TurmaService {
  @Autowired
  private TurmaDao repo;

  @Autowired
  private AlunoDao alunoRepo;

  @Autowired
  private MateriaDao materiaRepo;

  private Turma turma;

  public Turma find(Long id) {
    return repo.findById(id).get();
  }

  public Turma create(TurmaDto params) {
    turma = new Turma();
    apply(params);
    return repo.save(turma);
  }

  public Turma update(Long id, TurmaDto params) {
    turma = repo.findById(id).get();
    apply(params);
    return repo.save(turma);
  }

  public void delete(Long id) {
    repo.deleteById(id);
  }

  public List<Turma> all() {
    return (List<Turma>) repo.findAll();
  }

  private void apply(TurmaDto params) {
    turma.setName(params.name);
    if (params.alunoIds != null)
      turma.setAlunos(params.alunoIds.stream().map(x -> alunoRepo.findById(x).get()).collect(Collectors.toList()));
    if (params.materiaIds != null)
      turma
          .setMaterias(params.materiaIds.stream().map(x -> materiaRepo.findById(x).get()).collect(Collectors.toList()));
  }
}
