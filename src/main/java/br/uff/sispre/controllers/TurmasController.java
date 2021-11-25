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

import br.uff.sispre.models.Turma;
import br.uff.sispre.services.TurmaService;

@RestController
@RequestMapping(path = "/turmas")
public class TurmasController {
  @Autowired
  private TurmaService turmaService;

  @PostMapping
  public Turma create(@RequestBody Turma turma) {
    turmaService.create(turma);
    return turma;
  }

  @GetMapping
  public Iterable<Turma> index() {
    return turmaService.all();
  }

  @GetMapping(path = "/{id}")
  public Turma show(@PathVariable Long id) {
    return turmaService.find(id);
  }

  @PatchMapping(path = "/{id}")
  public Turma update(@RequestBody Turma turma, @PathVariable Long id) {
    turmaService.update(id, turma);
    return turma;
  }

  @DeleteMapping(path = "/{id}")
  public String delete(@PathVariable Long id) {
    turmaService.delete(id);
    return "Deleted";
  }
}
