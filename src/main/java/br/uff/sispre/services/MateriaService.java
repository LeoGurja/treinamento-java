package br.uff.sispre.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.uff.sispre.models.Materia;
import br.uff.sispre.models.Professor;
import br.uff.sispre.repositories.MateriaRepository;
import br.uff.sispre.repositories.ProfessorRepository;

@Service
public class MateriaService {
  @Autowired
  private MateriaRepository repo;

  @Autowired
  private ProfessorRepository professorRepo;

  public Materia find(Long id) {
    return repo.findById(id).get();
  }

  public void create(Materia materia) {
    setProfessor(materia);
    repo.save(materia);
  }

  public void update(Long id, Materia materia) {
    setProfessor(materia);
    repo.save(materia);
  }

  public void delete(Long id) {
    repo.deleteById(id);
  }

  public List<Materia> all() {
    return (List<Materia>) repo.findAll();
  }

  private void setProfessor(Materia materia) {
    Optional<Professor> professor = professorRepo.findById(materia.getProfessorId());
    if (professor.isPresent())
      materia.setProfessor(professor.get());
  }
}
