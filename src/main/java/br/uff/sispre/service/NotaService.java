package br.uff.sispre.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.uff.sispre.controller.dto.NotaDto;
import br.uff.sispre.dao.AlunoDao;
import br.uff.sispre.dao.MateriaDao;
import br.uff.sispre.dao.NotaDao;
import br.uff.sispre.model.Nota;

@Service
public class NotaService {
  @Autowired
  private NotaDao notaDao;

  @Autowired
  private AlunoDao alunoDao;

  @Autowired
  private MateriaDao materiaDao;

  private Nota nota;

  public Nota porId(Long id) {
    return notaDao.findById(id).get();
  }

  public Nota criaNota(NotaDto params) {
    validaAluno(params);
    validaMateria(params);

    nota = new Nota();
    aplicaParametros(params);
    try {
      return notaDao.save(nota);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi possível criar a nota!");
    }
  }

  public Nota alteraNota(Long id, NotaDto params) {
    validaAluno(params);
    validaMateria(params);

    nota = notaDao.findById(id).get();
    aplicaParametros(params);
    try {
      return notaDao.save(nota);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi possível alterar a nota!");
    }
  }

  public void deletaNota(Long id) {
    notaDao.deleteById(id);
  }

  public List<Nota> listaNotas() {
    return (List<Nota>) notaDao.findAll();
  }

  private void aplicaParametros(NotaDto params) {
    nota.setNota(params.nota);
    nota.setAluno(alunoDao.findById(params.alunoId).get());
    nota.setMateria(materiaDao.findById(params.materiaId).get());
  }

  private void validaAluno(NotaDto params) {
    if (params.alunoId == null || !alunoDao.findById(params.alunoId).isPresent())
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possível encontrar o aluno!");
  }

  private void validaMateria(NotaDto params) {
    if (params.materiaId == null || !materiaDao.findById(params.materiaId).isPresent())
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possível encontrar a matéria!");
  }
}
