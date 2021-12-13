package br.uff.sispre.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.uff.sispre.controller.dto.AlunoDto;
import br.uff.sispre.dao.AlunoDao;
import br.uff.sispre.dao.TurmaDao;
import br.uff.sispre.helper.Sanitizer;
import br.uff.sispre.helper.Sha256;
import br.uff.sispre.model.Aluno;

@Service
public class AlunoService {
  @Autowired
  private AlunoDao repo;

  @Autowired
  private TurmaDao turmaRepo;

  private Aluno aluno;

  public Aluno find(Long id) {
    return repo.findById(id).get();
  }

  public Aluno create(AlunoDto params) {
    aluno = new Aluno();
    apply(params);
    try {
      return repo.save(aluno);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não é possível criar o aluno!");
    }
  }

  public Aluno update(Long id, AlunoDto params) {
    aluno = repo.findById(id).get();
    validateOnUpdate(params);
    apply(params);
    return repo.save(aluno);
  }

  public void delete(Long id) {
    repo.deleteById(id);
  }

  public List<Aluno> all() {
    return (List<Aluno>) repo.findAll();
  }

  void apply(AlunoDto params) {
    aluno.setCpf(Sanitizer.sanitize(Sanitizer.cpf, params.cpf));
    aluno.setRg(Sanitizer.sanitize(Sanitizer.rg, params.rg));
    aluno.setName(params.name);
    aluno.setAddress(params.address);
    aluno.setPhoneNumber(Sanitizer.sanitize(Sanitizer.phoneNumber, params.phoneNumber));
    aluno.setEmail(params.email);
    aluno.setPasswordDigest(Sha256.encryptPassword(params.password));
    if (params.turmaId != null)
      aluno.setTurma(turmaRepo.findById(params.turmaId));
  }

  private void validateOnUpdate(AlunoDto params) {
    if (!aluno.getCpf().equals(params.cpf))
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não é possível alterar o cpf do aluno!");
  }
}
