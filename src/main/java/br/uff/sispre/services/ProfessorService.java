package br.uff.sispre.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.uff.sispre.helpers.Sanitizer;
import br.uff.sispre.helpers.Sha256;
import br.uff.sispre.models.Professor;
import br.uff.sispre.repositories.ProfessorRepository;

@Service
public class ProfessorService {
  @Autowired
  private ProfessorRepository professorRepository;

  public Professor find(Long id) {
    return professorRepository.findById(id).get();
  }

  public void create(Professor professor) {
    sanitize(professor);
    professorRepository.save(professor);
  }

  public void update(Long id, Professor professor) {
    sanitize(professor);
    compare(professor, id);
    professorRepository.save(professor);
  }

  public void delete(Long id) {
    professorRepository.deleteById(id);
  }

  public Iterable<Professor> all() {
    return professorRepository.findAll();
  }

  private void sanitize(Professor professor) {
    professor.setCpf(Sanitizer.sanitizeCpf(professor.getCpf()));
    professor.setRg(Sanitizer.sanitizeRg(professor.getRg()));
    professor.setPhoneNumber(Sanitizer.sanitizePhoneNumber(professor.getPhoneNumber()));
    professor.setPasswordDigest(Sha256.encryptPassword(professor.getPassword()));
  }

  private void compare(Professor professor, Long id) {
    Professor oldProfessor = professorRepository.findById(id).get();
    if (!professor.getCpf().equals(oldProfessor.getCpf()))
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não é possível alterar o cpf do professor!");
  }
}
