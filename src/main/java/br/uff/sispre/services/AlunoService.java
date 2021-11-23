package br.uff.sispre.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.uff.sispre.helpers.Sanitizer;
import br.uff.sispre.helpers.Sha256;
import br.uff.sispre.models.Aluno;
import br.uff.sispre.repositories.AlunoRepository;

@Service
public class AlunoService {
  @Autowired
  private AlunoRepository alunoRepository;

  public Aluno find(Long id) {
    return alunoRepository.findById(id).get();
  }

  public void create(Aluno aluno) {
    sanitize(aluno);
    alunoRepository.save(aluno);
  }

  public void update(Long id, Aluno aluno) {
    sanitize(aluno);
    compare(aluno, id);
    alunoRepository.save(aluno);
  }

  public void delete(Long id) {
    alunoRepository.deleteById(id);
  }

  public Iterable<Aluno> all() {
    return alunoRepository.findAll();
  }

  private void sanitize(Aluno aluno) {
    aluno.setCpf(Sanitizer.sanitizeCpf(aluno.getCpf()));
    aluno.setRg(Sanitizer.sanitizeRg(aluno.getRg()));
    aluno.setPhoneNumber(Sanitizer.sanitizePhoneNumber(aluno.getPhoneNumber()));
    aluno.setPasswordDigest(Sha256.encryptPassword(aluno.getPassword()));
  }

  private void compare(Aluno aluno, Long id) {
    Aluno oldAluno = alunoRepository.findById(id).get();
    if (!aluno.getCpf().equals(oldAluno.getCpf()))
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não é possível alterar o cpf do aluno!");
  }
}
