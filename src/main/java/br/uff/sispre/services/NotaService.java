package br.uff.sispre.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.uff.sispre.models.Nota;
import br.uff.sispre.repositories.AlunoRepository;
import br.uff.sispre.repositories.MateriaRepository;
import br.uff.sispre.repositories.NotaRepository;

@Service
public class NotaService {
  @Autowired
  private NotaRepository repo;

  @Autowired
  private AlunoRepository alunoRepo;

  @Autowired
  private MateriaRepository materiaRepo;

  public Nota find(Long id) {
    return repo.findById(id).get();
  }

  public void create(Nota nota) {
    nota.setAluno(alunoRepo.findById(nota.getAlunoId()).get());
    nota.setMateria(materiaRepo.findById(nota.getMateriaId()).get());
    repo.save(nota);
  }

  public void update(Long id, Nota nota) {
    nota.setAluno(alunoRepo.findById(nota.getAlunoId()).get());
    nota.setMateria(materiaRepo.findById(nota.getMateriaId()).get());
    repo.save(nota);
  }

  public void delete(Long id) {
    repo.deleteById(id);
  }

  public List<Nota> all() {
    return (List<Nota>) repo.findAll();
  }
}
