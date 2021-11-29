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
import org.springframework.web.server.ResponseStatusException;

import br.uff.sispre.controllers.resources.ProfessorResource;
import br.uff.sispre.services.ProfessorService;

@RestController
@RequestMapping(path = "/professores")
public class ProfessoresController {
  @Autowired
  private ProfessorService professorService;

  @PostMapping
  public ProfessorResource create(@RequestBody ProfessorResource params) {
    try {
      return new ProfessorResource(professorService.create(params));
    } catch (Exception e) {
      throw new ResponseStatusException(400, "Não foi possível criar o professor!", e);
    }
  }

  @GetMapping
  public List<ProfessorResource> index() {
    return professorService.all().stream().map(professor -> new ProfessorResource(professor))
        .collect(Collectors.toList());
  }

  @GetMapping(path = "/{id}")
  public ProfessorResource show(@PathVariable Long id) {
    return new ProfessorResource(professorService.find(id));
  }

  @PatchMapping(path = "/{id}")
  public ProfessorResource update(@RequestBody ProfessorResource params, @PathVariable Long id) {
    try {
      return new ProfessorResource(professorService.update(id, params));
    } catch (Exception e) {
      throw new ResponseStatusException(400, "Não foi possível atualizar o professor!", e);
    }
  }

  @DeleteMapping(path = "/{id}")
  public String delete(@PathVariable Long id) {
    professorService.delete(id);
    return "Deleted";
  }
}
