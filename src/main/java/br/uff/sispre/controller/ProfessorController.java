package br.uff.sispre.controller;

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

import br.uff.sispre.controller.dto.ProfessorDto;
import br.uff.sispre.service.ProfessorService;

@RestController
@RequestMapping(path = "/professores")
public class ProfessorController {
  @Autowired
  private ProfessorService professorService;

  @PostMapping
  public ProfessorDto create(@RequestBody ProfessorDto params) {
    try {
      return new ProfessorDto(professorService.create(params));
    } catch (Exception e) {
      throw new ResponseStatusException(400, "Não foi possível criar o professor!", e);
    }
  }

  @GetMapping
  public List<ProfessorDto> index() {
    return professorService.all().stream().map(professor -> new ProfessorDto(professor))
        .collect(Collectors.toList());
  }

  @GetMapping(path = "/{id}")
  public ProfessorDto show(@PathVariable Long id) {
    return new ProfessorDto(professorService.find(id));
  }

  @PatchMapping(path = "/{id}")
  public ProfessorDto update(@RequestBody ProfessorDto params, @PathVariable Long id) {
    try {
      return new ProfessorDto(professorService.update(id, params));
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
