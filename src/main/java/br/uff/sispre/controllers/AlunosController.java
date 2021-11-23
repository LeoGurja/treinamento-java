package br.uff.sispre.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.uff.sispre.models.Aluno;
import br.uff.sispre.services.AlunoService;

@RestController
@RequestMapping(path = "/alunos")
public class AlunosController {
  @Autowired
  private AlunoService alunoService;

  @PostMapping
  public String create(@RequestBody Aluno aluno) {
    alunoService.create(aluno);
    return "Saved";
  }

  @GetMapping
  public Iterable<Aluno> index() {
    return alunoService.all();
  }

  @GetMapping(path = "/{id}")
  public Aluno show(@PathVariable Long id) {
    return alunoService.find(id);
  }

  @PatchMapping(path = "/{id}")
  public String update(@RequestBody Aluno aluno, @PathVariable Long id) {
    alunoService.update(id, aluno);
    return "Updated";
  }

  @DeleteMapping(path = "/{id}")
  public String delete(@PathVariable Long id) {
    alunoService.delete(id);
    return "Deleted";
  }
}
