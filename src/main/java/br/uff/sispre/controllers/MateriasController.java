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

import br.uff.sispre.controllers.resources.MateriaResource;
import br.uff.sispre.services.MateriaService;

@RestController
@RequestMapping(path = "/materias")
public class MateriasController {
  @Autowired
  private MateriaService materiaService;

  @PostMapping
  public MateriaResource create(@RequestBody MateriaResource params) {
    try {
      return new MateriaResource(materiaService.create(params));
    } catch (Exception e) {
      throw new ResponseStatusException(400, "Não foi possível criar a matéria!", e);
    }
  }

  @GetMapping
  public List<MateriaResource> index() {
    return materiaService.all().stream().map(x -> new MateriaResource(x)).collect(Collectors.toList());
  }

  @GetMapping(path = "/{id}")
  public MateriaResource show(@PathVariable Long id) {
    return new MateriaResource(materiaService.find(id));
  }

  @PatchMapping(path = "/{id}")
  public MateriaResource update(@RequestBody MateriaResource params, @PathVariable Long id) {
    try {
      return new MateriaResource(materiaService.update(id, params));
    } catch (Exception e) {
      throw new ResponseStatusException(400, "Não foi possível atualizar a matéria!", e);
    }
  }

  @DeleteMapping(path = "/{id}")
  public String delete(@PathVariable Long id) {
    materiaService.delete(id);
    return "Deleted";
  }
}
