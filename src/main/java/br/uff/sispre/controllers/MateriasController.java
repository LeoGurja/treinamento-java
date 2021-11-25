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

import br.uff.sispre.controllers.resources.MateriaResource;
import br.uff.sispre.models.Materia;
import br.uff.sispre.services.MateriaService;

@RestController
@RequestMapping(path = "/materias")
public class MateriasController {
  @Autowired
  private MateriaService materiaService;

  @PostMapping
  public MateriaResource create(@RequestBody Materia materia) {
    System.out.println(materia.getName());
    materiaService.create(materia);
    return new MateriaResource(materia);
  }

  @GetMapping
  public Set<MateriaResource> index() {
    return materiaService.all().stream().map(materia -> new MateriaResource(materia)).collect(Collectors.toSet());
  }

  @GetMapping(path = "/{id}")
  public MateriaResource show(@PathVariable Long id) {
    return new MateriaResource(materiaService.find(id));
  }

  @PatchMapping(path = "/{id}")
  public MateriaResource update(@RequestBody Materia materia, @PathVariable Long id) {
    materiaService.update(id, materia);
    return new MateriaResource(materia);
  }

  @DeleteMapping(path = "/{id}")
  public String delete(@PathVariable Long id) {
    materiaService.delete(id);
    return "Deleted";
  }
}
