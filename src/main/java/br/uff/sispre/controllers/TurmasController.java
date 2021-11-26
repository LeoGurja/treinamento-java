package br.uff.sispre.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.uff.sispre.controllers.resources.TurmaResource;
import br.uff.sispre.services.TurmaService;

@RestController
@RequestMapping(path = "/turmas")
public class TurmasController {
  @Autowired
  private TurmaService turmaService;

  @PostMapping
  public TurmaResource create(@RequestBody TurmaResource turma) {
    return new TurmaResource(turmaService.create(turma));
  }

  @GetMapping
  public List<TurmaResource> index() {
    return turmaService.all().stream().map(turma -> new TurmaResource(turma)).collect(Collectors.toList());
  }

  @GetMapping(path = "/{id}")
  public TurmaResource show(@PathVariable Long id) {
    return new TurmaResource(turmaService.find(id));
  }

  @PatchMapping(path = "/{id}")
  public TurmaResource update(@RequestBody TurmaResource turma, @PathVariable Long id) {
    return new TurmaResource(turmaService.update(id, turma));
  }

  @DeleteMapping(path = "/{id}")
  public String delete(@PathVariable Long id) {
    turmaService.delete(id);
    return "Deleted";
  }
}
