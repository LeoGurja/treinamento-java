package br.uff.sispre.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.uff.sispre.models.Materia;
import br.uff.sispre.services.MateriaService;

@RestController("/materias")
public class MateriasController {
  @Autowired
  private MateriaService materiaService;

  @PostMapping
  public Materia create(@RequestBody Materia materia) {
    materiaService.create(materia);
    return materia;
  }

  @GetMapping
  public Iterable<Materia> index() {
    return materiaService.all();
  }

  @GetMapping(path = "/{id}")
  public Materia show(@PathVariable Long id) {
    return materiaService.find(id);
  }

  @PatchMapping(path = "/{id}")
  public Materia update(@RequestBody Materia materia, @PathVariable Long id) {
    materiaService.update(id, materia);
    return materia;
  }

  @DeleteMapping(path = "/{id}")
  public String delete(@PathVariable Long id) {
    materiaService.delete(id);
    return "Deleted";
  }
}
