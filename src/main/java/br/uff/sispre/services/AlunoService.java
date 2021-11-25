package br.uff.sispre.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.uff.sispre.controllers.forms.AlunoForm;
import br.uff.sispre.helpers.Sanitizer;
import br.uff.sispre.helpers.Sha256;
import br.uff.sispre.models.Aluno;
import br.uff.sispre.models.Turma;
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

  public void create(AlunoForm alunoForm) {
    aluno = new Aluno();
    apply(alunoForm);
    repo.save(aluno);
  }

  public void update(Long id, AlunoForm alunoForm) {
    aluno = repo.findById(id).get();
    validateOnUpdate(alunoForm);
    apply(alunoForm);
    repo.save(aluno);
  }

  public void delete(Long id) {
    repo.deleteById(id);
  }

  public List<Aluno> all() {
    return (List<Aluno>) repo.findAll();
  }

  private void apply(AlunoForm alunoForm) {
    aluno.setCpf(Sanitizer.sanitizeCpf(alunoForm.cpf));
    aluno.setRg(Sanitizer.sanitizeRg(alunoForm.rg));
    aluno.setName(alunoForm.name);
    aluno.setPhoneNumber(Sanitizer.sanitizePhoneNumber(alunoForm.phoneNumber));
    aluno.setPasswordDigest(Sha256.encryptPassword(alunoForm.password));
    aluno.setAddress(alunoForm.address);
    aluno.setEmail(alunoForm.email);
    setTurma(alunoForm);
  }

  private void validateOnUpdate(AlunoForm alunoForm) {
    if (!aluno.getCpf().equals(alunoForm.cpf))
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não é possível alterar o cpf do aluno!");
  }

  private void setTurma(AlunoForm alunoForm) {
    Optional<Turma> turma = turmaRepo.findById(aluno.getTurmaId());
    if (turma.isPresent())
      aluno.setTurma(turma.get());
  }
}
