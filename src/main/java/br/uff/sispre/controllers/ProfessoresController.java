package br.uff.sispre.controllers;

import java.util.Set;
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

import br.uff.sispre.controllers.resources.ProfessorResource;
import br.uff.sispre.models.Professor;
import br.uff.sispre.services.ProfessorService;

@RestController
@RequestMapping(path = "/professores")
public class ProfessoresController {
  @Autowired
  private ProfessorService professorService;

  @PostMapping
  public ProfessorResource create(@RequestBody Professor professor) {
    professorService.create(professor);
    return new ProfessorResource(professor);
  }

  @GetMapping
  public Set<ProfessorResource> index() {
    return professorService.all().stream().map(professor -> new ProfessorResource(professor))
        .collect(Collectors.toSet());
  }

  @GetMapping(path = "/{id}")
  public ProfessorResource show(@PathVariable Long id) {
    return new ProfessorResource(professorService.find(id));
  }

  @PatchMapping(path = "/{id}")
  public ProfessorResource update(@RequestBody Professor professor, @PathVariable Long id) {
    professorService.update(id, professor);
    return new ProfessorResource(professor);
  }

  @DeleteMapping(path = "/{id}")
  public String delete(@PathVariable Long id) {
    professorService.delete(id);
    return "Deleted";
  }
}
