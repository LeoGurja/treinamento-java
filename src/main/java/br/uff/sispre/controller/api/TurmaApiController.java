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

import br.uff.sispre.controller.dto.TurmaDto;
import br.uff.sispre.service.TurmaService;

@RestController
@RequestMapping("/api/turmas")
public class TurmaApiController {
  @Autowired
  private TurmaService turmaService;

  @PostMapping
  public TurmaDto create(@RequestBody TurmaDto turma) {
    return new TurmaDto(turmaService.create(turma));
  }

  @GetMapping
  public List<TurmaDto> index() {
    return turmaService.all().stream().map(turma -> new TurmaDto(turma)).collect(Collectors.toList());
  }

  @GetMapping("/{id}")
  public TurmaDto show(@PathVariable Long id) {
    return new TurmaDto(turmaService.find(id));
  }

  @PatchMapping("/{id}")
  public TurmaDto update(@RequestBody TurmaDto turma, @PathVariable Long id) {
    return new TurmaDto(turmaService.update(id, turma));
  }

  @DeleteMapping("/{id}")
  public String delete(@PathVariable Long id) {
    turmaService.delete(id);
    return "Deleted";
  }
}
