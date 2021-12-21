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
  public TurmaDto criaTurma(@RequestBody TurmaDto turma) {
    return new TurmaDto(turmaService.criaTurma(turma));
  }

  @GetMapping
  public List<TurmaDto> listaTurmas() {
    return turmaService.listaTurmas().stream().map(turma -> new TurmaDto(turma)).collect(Collectors.toList());
  }

  @GetMapping("/{id}")
  public TurmaDto mostraTurma(@PathVariable Long id) {
    return new TurmaDto(turmaService.porId(id));
  }

  @PatchMapping("/{id}")
  public TurmaDto alteraTurma(@RequestBody TurmaDto turma, @PathVariable Long id) {
    return new TurmaDto(turmaService.alteraTurma(id, turma));
  }

  @DeleteMapping("/{id}")
  public String deletaTurma(@PathVariable Long id) {
    turmaService.deletaTurma(id);
    return "Deleted";
  }
}
