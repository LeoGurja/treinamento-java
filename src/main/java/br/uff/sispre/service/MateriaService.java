package br.uff.sispre.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
  private MateriaDao materiaDao;

  @Autowired
  private ProfessorDao professorDao;

  @Autowired
  private TurmaDao turmaDao;

  private Materia materia;

  public Materia porId(Long id) {
    return materiaDao.findById(id).get();
  }

  public Materia criaMateria(MateriaDto params) {
    materia = new Materia();
    aplicaParametros(params);
    try {
      return materiaDao.save(materia);
    } catch (DataIntegrityViolationException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi possível criar a materia!");
    }
  }

  public Materia alteraMateria(Long id, MateriaDto params) {
    materia = materiaDao.findById(id).get();
    aplicaParametros(params);
    try {
      return materiaDao.save(materia);
    } catch (DataIntegrityViolationException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi possível alterar a materia!");
    }
  }

  public void deletaMateria(Long id) {
    materiaDao.deleteById(id);
  }

  public List<Materia> listaMaterias() {
    return (List<Materia>) materiaDao.findAll();
  }

  private void aplicaParametros(MateriaDto params) {
    materia.setNome(params.nome);
    materia.setDescricao(params.descricao);
    if (params.turmaIds != null)
      materia.setTurmas(params.turmaIds.stream().map(id -> turmaDao.findById(id).get()).collect(Collectors.toList()));
    if (params.professorId != null)
      materia.setProfessor(professorDao.findById(params.professorId));
  }
}
