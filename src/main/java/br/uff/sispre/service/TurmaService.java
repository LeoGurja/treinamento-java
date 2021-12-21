package br.uff.sispre.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.uff.sispre.controller.dto.TurmaDto;
import br.uff.sispre.dao.AlunoDao;
import br.uff.sispre.dao.MateriaDao;
import br.uff.sispre.dao.TurmaDao;
import br.uff.sispre.model.Turma;

@Service
public class TurmaService {
  @Autowired
  private TurmaDao turmaDao;

  @Autowired
  private AlunoDao alunoDao;

  @Autowired
  private MateriaDao materiaDao;

  private Turma turma;

  public Turma porId(Long id) {
    return turmaDao.findById(id).get();
  }

  public Turma criaTurma(TurmaDto params) {
    turma = new Turma();
    aplicaParametros(params);
    try {
      return turmaDao.save(turma);
    } catch (DataIntegrityViolationException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi possível criar a turma!");
    }
  }

  public Turma alteraTurma(Long id, TurmaDto params) {
    turma = turmaDao.findById(id).get();
    aplicaParametros(params);
    try {
      return turmaDao.save(turma);
    } catch (DataIntegrityViolationException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi possível alterar a turma!");
    }
  }

  public void deletaTurma(Long id) {
    turmaDao.deleteById(id);
  }

  public List<Turma> listaTurmas() {
    return (List<Turma>) turmaDao.findAll();
  }

  private void aplicaParametros(TurmaDto params) {
    turma.setNome(params.nome);
    if (params.alunoIds != null)
      turma.setAlunos(params.alunoIds.stream().map(x -> alunoDao.findById(x).get()).collect(Collectors.toList()));
    if (params.materiaIds != null)
      turma
          .setMaterias(params.materiaIds.stream().map(x -> materiaDao.findById(x).get()).collect(Collectors.toList()));
  }
}
