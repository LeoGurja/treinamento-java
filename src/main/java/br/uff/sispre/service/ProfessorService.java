package br.uff.sispre.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
  private ProfessorDao repo;

  @Autowired
  private MateriaDao materiaRepo;

  private Professor professor;

  public Professor find(Long id) {
    return repo.findById(id).get();
  }

  public Professor create(ProfessorDto params) {
    professor = new Professor();
    apply(params);
    return repo.save(professor);
  }

  public Professor update(Long id, ProfessorDto params) {
    professor = repo.findById(id).get();
    validateOnUpdate(params);
    apply(params);
    return repo.save(professor);
  }

  public void delete(Long id) {
    repo.deleteById(id);
  }

  public List<Professor> all() {
    return (List<Professor>) repo.findAll();
  }

  private void apply(ProfessorDto params) {
    professor.setCpf(Sanitizer.sanitize(Sanitizer.cpf, params.cpf));
    professor.setRg(Sanitizer.sanitize(Sanitizer.rg, params.rg));
    professor.setName(params.name);
    professor.setAddress(params.address);
    professor.setPhoneNumber(Sanitizer.sanitize(Sanitizer.phoneNumber, params.phoneNumber));
    professor.setEmail(params.email);
    professor.setPasswordDigest(Sha256.encryptPassword(params.password));
    if (params.materiaId != null)
      professor.setMateria(materiaRepo.findById(params.materiaId));
  }

  private void validateOnUpdate(ProfessorDto params) {
    if (!params.cpf.equals(professor.getCpf()))
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não é possível alterar o cpf do professor!");
  }
}
