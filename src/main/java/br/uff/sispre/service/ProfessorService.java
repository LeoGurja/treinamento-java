package br.uff.sispre.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.uff.sispre.controller.dto.ProfessorDto;
import br.uff.sispre.dao.MateriaDao;
import br.uff.sispre.dao.ProfessorDao;
import br.uff.sispre.helper.Sanitizer;
import br.uff.sispre.helper.Sha256;
import br.uff.sispre.model.Professor;

@Service
public class ProfessorService {
  @Autowired
  private ProfessorDao professorDao;

  @Autowired
  private MateriaDao materiaDao;

  private Professor professor;

  public Professor porId(Long id) {
    return professorDao.findById(id).get();
  }

  public Professor criaProfessor(ProfessorDto params) {
    professor = new Professor();
    aplicaParametros(params);
    try {
      return professorDao.save(professor);
    } catch (DataIntegrityViolationException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi possível criar o professor!");
    }
  }

  public Professor alteraProfessor(Long id, ProfessorDto params) {
    professor = professorDao.findById(id).get();
    validaAlteracao(params);
    aplicaParametros(params);
    try {
      return professorDao.save(professor);
    } catch (DataIntegrityViolationException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi possível alterar o professor!");
    }
  }

  public void deletaProfessor(Long id) {
    professorDao.deleteById(id);
  }

  public List<Professor> listaProfessores() {
    return (List<Professor>) professorDao.findAll();
  }

  private void aplicaParametros(ProfessorDto params) {
    professor.setCpf(Sanitizer.sanitize(Sanitizer.cpf, params.cpf));
    professor.setRg(Sanitizer.sanitize(Sanitizer.rg, params.rg));
    professor.setNome(params.nome);
    professor.setEndereco(params.endereco);
    professor.setTelefone(Sanitizer.sanitize(Sanitizer.telefone, params.telefone));
    professor.setEmail(params.email);
    professor.setHashSenha(Sha256.encryptPassword(params.senha));
    if (params.materiaId != null)
      professor.setMateria(materiaDao.findById(params.materiaId));
  }

  private void validaAlteracao(ProfessorDto params) {
    if (!params.cpf.equals(professor.getCpf()))
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não é possível alterar o cpf do professor!");
  }
}
