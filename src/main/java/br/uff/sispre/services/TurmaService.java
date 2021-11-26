package br.uff.sispre.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.uff.sispre.controllers.resources.TurmaResource;
import br.uff.sispre.models.Turma;
import br.uff.sispre.repositories.AlunoRepository;
import br.uff.sispre.repositories.MateriaRepository;
import br.uff.sispre.repositories.TurmaRepository;

@Service
public class TurmaService {
  @Autowired
  private TurmaRepository repo;

  @Autowired
  private AlunoRepository alunoRepo;

  @Autowired
  private MateriaRepository materiaRepo;

  private Turma turma;

  public Turma find(Long id) {
    return repo.findById(id).get();
  }

  public Turma create(TurmaResource params) {
    turma = new Turma();
    apply(params);
    return repo.save(turma);
  }

  public Turma update(Long id, TurmaResource params) {
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

  private void apply(TurmaResource params) {
    turma.setName(params.name);
    if (params.alunoIds != null)
      turma.setAlunos(params.alunoIds.stream().map(x -> alunoRepo.findById(x).get()).collect(Collectors.toList()));
    if (params.materiaIds != null)
      turma
          .setMaterias(params.materiaIds.stream().map(x -> materiaRepo.findById(x).get()).collect(Collectors.toList()));
  }
}
