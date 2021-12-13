package br.uff.sispre.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.uff.sispre.controller.dto.MateriaDto;
import br.uff.sispre.dao.MateriaDao;
import br.uff.sispre.dao.ProfessorDao;
import br.uff.sispre.dao.TurmaDao;
import br.uff.sispre.model.Materia;

@Service
public class MateriaService {
  @Autowired
  private MateriaDao repo;

  @Autowired
  private ProfessorDao professorRepo;

  @Autowired
  private TurmaDao turmaRepo;

  private Materia materia;

  public Materia find(Long id) {
    return repo.findById(id).get();
  }

  public Materia create(MateriaDto params) {
    materia = new Materia();
    apply(params);
    try {
      return repo.save(materia);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não é possível criar a materia!");
    }
  }

  public Materia update(Long id, MateriaDto params) {
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

  private void apply(MateriaDto params) {
    materia.setName(params.name);
    materia.setDescription(params.description);
    if (params.turmaIds != null)
      materia.setTurmas(params.turmaIds.stream().map(id -> turmaRepo.findById(id).get()).collect(Collectors.toList()));
    if (params.professorId != null)
      materia.setProfessor(professorRepo.findById(params.professorId));
  }
}
