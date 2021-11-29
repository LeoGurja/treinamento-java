package br.uff.sispre.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.uff.sispre.controllers.resources.AlunoResource;
import br.uff.sispre.helpers.Sanitizer;
import br.uff.sispre.helpers.Sha256;
import br.uff.sispre.models.Aluno;
import br.uff.sispre.repositories.AlunoRepository;
import br.uff.sispre.repositories.TurmaRepository;

@Service
public class AlunoService {
  @Autowired
  private AlunoRepository repo;

  @Autowired
  private TurmaRepository turmaRepo;

  private Aluno aluno;

  public Aluno find(Long id) {
    return repo.findById(id).get();
  }

  public Aluno create(AlunoResource params) {
    aluno = new Aluno();
    apply(params);
    return repo.save(aluno);
  }

  public Aluno update(Long id, AlunoResource params) {
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

  void apply(AlunoResource params) {
    aluno.setCpf(Sanitizer.sanitize(Sanitizer.cpf, params.cpf));
    aluno.setRg(Sanitizer.sanitize(Sanitizer.rg, params.rg));
    aluno.setName(params.name);
    aluno.setAddress(params.address);
    aluno.setPhoneNumber(Sanitizer.sanitize(Sanitizer.phoneNumber, params.phoneNumber));
    aluno.setEmail(params.email);
    aluno.setPasswordDigest(Sha256.encryptPassword(params.password));
    if (params.turmaId != null)
      aluno.setTurma(turmaRepo.findById(params.turmaId).orElse(null));
  }

  private void validateOnUpdate(AlunoResource params) {
    if (!aluno.getCpf().equals(params.cpf))
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não é possível alterar o cpf do aluno!");
  }
}
