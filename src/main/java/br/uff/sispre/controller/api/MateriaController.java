package br.uff.sispre.controller.api;

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

import br.uff.sispre.controller.dto.MateriaDto;
import br.uff.sispre.service.MateriaService;

@RestController
@RequestMapping(path = "/api/materias")
public class MateriaController {
  @Autowired
  private MateriaService materiaService;

  @PostMapping
  public MateriaDto create(@RequestBody MateriaDto params) {
    try {
      return new MateriaDto(materiaService.create(params));
    } catch (Exception e) {
      throw new ResponseStatusException(400, "Não foi possível criar a matéria!", e);
    }
  }

  @GetMapping
  public List<MateriaDto> index() {
    return materiaService.all().stream().map(x -> new MateriaDto(x)).collect(Collectors.toList());
  }

  @GetMapping(path = "/{id}")
  public MateriaDto show(@PathVariable Long id) {
    return new MateriaDto(materiaService.find(id));
  }

  @PatchMapping(path = "/{id}")
  public MateriaDto update(@RequestBody MateriaDto params, @PathVariable Long id) {
    try {
      return new MateriaDto(materiaService.update(id, params));
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
