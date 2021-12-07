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

import br.uff.sispre.controller.dto.TurmaDto;
import br.uff.sispre.service.TurmaService;

@RestController
@RequestMapping(path = "/api/turmas")
public class TurmaController {
  @Autowired
  private TurmaService turmaService;

  @PostMapping
  public TurmaDto create(@RequestBody TurmaDto turma) {
    try {
      return new TurmaDto(turmaService.create(turma));
    } catch (Exception e) {
      throw new ResponseStatusException(400, "Não foi possível criar a turma!", e);
    }
  }

  @GetMapping
  public List<TurmaDto> index() {
    return turmaService.all().stream().map(turma -> new TurmaDto(turma)).collect(Collectors.toList());
  }

  @GetMapping(path = "/{id}")
  public TurmaDto show(@PathVariable Long id) {
    return new TurmaDto(turmaService.find(id));
  }

  @PatchMapping(path = "/{id}")
  public TurmaDto update(@RequestBody TurmaDto turma, @PathVariable Long id) {
    try {
      return new TurmaDto(turmaService.update(id, turma));
    } catch (Exception e) {
      throw new ResponseStatusException(400, "Não foi possível atualizar a turma!", e);
    }
  }

  @DeleteMapping(path = "/{id}")
  public String delete(@PathVariable Long id) {
    turmaService.delete(id);
    return "Deleted";
  }
}
