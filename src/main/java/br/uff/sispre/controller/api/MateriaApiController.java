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
@RequestMapping("/api/materias")
public class MateriaApiController {
  @Autowired
  private MateriaService materiaService;

  @PostMapping
  public MateriaDto criaMateria(@RequestBody MateriaDto params) {
    return new MateriaDto(materiaService.criaMateria(params));
  }

  @GetMapping
  public List<MateriaDto> listaMaterias() {
    return materiaService.listaMaterias().stream().map(x -> new MateriaDto(x)).collect(Collectors.toList());
  }

  @GetMapping("/{id}")
  public MateriaDto mostraMateria(@PathVariable Long id) {
    return new MateriaDto(materiaService.porId(id));
  }

  @PatchMapping("/{id}")
  public MateriaDto alteraMateria(@RequestBody MateriaDto params, @PathVariable Long id) {
    return new MateriaDto(materiaService.alteraMateria(id, params));
  }

  @DeleteMapping("/{id}")
  public String deletaMateria(@PathVariable Long id) {
    materiaService.deletaMateria(id);
    return "Deleted";
  }
}
