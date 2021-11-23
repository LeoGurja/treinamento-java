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

import br.uff.sispre.models.Professor;
import br.uff.sispre.services.ProfessorService;

@RestController
@RequestMapping(path = "/professores")
public class ProfessoresController {
  @Autowired
  private ProfessorService professorService;

  @PostMapping
  public String create(@RequestBody Professor professor) {
    professorService.create(professor);
    return "Saved";
  }

  @GetMapping
  public Iterable<Professor> index() {
    return professorService.all();
  }

  @GetMapping(path = "/{id}")
  public Professor show(@PathVariable Long id) {
    return professorService.find(id);
  }

  @PatchMapping(path = "/{id}")
  public String update(@RequestBody Professor professor, @PathVariable Long id) {
    professorService.update(id, professor);
    return "Updated";
  }

  @DeleteMapping(path = "/{id}")
  public String delete(@PathVariable Long id) {
    professorService.delete(id);
    return "Deleted";
  }
}
