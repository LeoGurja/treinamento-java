package br.uff.sispre.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.uff.sispre.controllers.resources.MateriaResource;
import br.uff.sispre.models.Materia;
import br.uff.sispre.repositories.MateriaRepository;
import br.uff.sispre.repositories.ProfessorRepository;
import br.uff.sispre.repositories.TurmaRepository;

@Service
public class MateriaService {
  @Autowired
  private MateriaRepository repo;

  @Autowired
  private ProfessorRepository professorRepo;

  @Autowired
  private TurmaRepository turmaRepo;

  private Materia materia;

  public Materia find(Long id) {
    return repo.findById(id).get();
  }

  public Materia create(MateriaResource params) {
    materia = new Materia();
    apply(params);
    return repo.save(materia);
  }

  public Materia update(Long id, MateriaResource params) {
    materia = repo.findById(id).get();
    apply(params);
    return repo.save(materia);
  }

  public void delete(Long id) {
    repo.deleteById(id);
  }

  public List<Materia> all() {
    return (List<Materia>) repo.findAll();
  }

  private void apply(MateriaResource params) {
    materia.setName(params.name);
    materia.setDescription(params.description);
    if (params.turmaIds != null)
      materia.setTurmas(params.turmaIds.stream().map(id -> turmaRepo.findById(id).get()).collect(Collectors.toList()));
    if (params.professorId != null)
      materia.setProfessor(professorRepo.findById(params.professorId));
  }
}
