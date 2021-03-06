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

import br.uff.sispre.controller.dto.MateriaDto;
import br.uff.sispre.service.MateriaService;

@RestController
@RequestMapping(path = "/api/materias")
public class MateriaApiController {
  @Autowired
  private MateriaService materiaService;

  @PostMapping
  public MateriaDto create(@RequestBody MateriaDto params) {
    return new MateriaDto(materiaService.create(params));
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
    return new MateriaDto(materiaService.update(id, params));
  }

  @DeleteMapping(path = "/{id}")
  public String delete(@PathVariable Long id) {
    materiaService.delete(id);
    return "Deleted";
  }
}
