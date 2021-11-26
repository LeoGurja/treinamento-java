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

import br.uff.sispre.controllers.resources.AlunoResource;
import br.uff.sispre.services.AlunoService;

@RestController
@RequestMapping(path = "/alunos")
public class AlunosController {
  @Autowired
  private AlunoService alunoService;

  @PostMapping
  public AlunoResource create(@RequestBody AlunoResource params) {
    return new AlunoResource(alunoService.create(params));
  }

  @GetMapping
  public List<AlunoResource> index() {
    return alunoService.all().stream().map(x -> new AlunoResource(x)).collect(Collectors.toList());
  }

  @GetMapping(path = "/{id}")
  public AlunoResource show(@PathVariable Long id) {
    return new AlunoResource(alunoService.find(id));
  }

  @PatchMapping(path = "/{id}")
  public AlunoResource update(@RequestBody AlunoResource aluno, @PathVariable Long id) {
    return new AlunoResource(alunoService.update(id, aluno));
  }

  @DeleteMapping(path = "/{id}")
  public String delete(@PathVariable Long id) {
    alunoService.delete(id);
    return "Deleted";
  }
}
