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

import br.uff.sispre.controller.dto.AlunoDto;
import br.uff.sispre.service.AlunoService;

@RestController
@RequestMapping("/api/alunos")
public class AlunoApiController {
  @Autowired
  private AlunoService alunoService;

  @PostMapping
  public AlunoDto create(@RequestBody AlunoDto params) {
    return new AlunoDto(alunoService.create(params));
  }

  @GetMapping
  public List<AlunoDto> index() {
    return alunoService.all().stream().map(x -> new AlunoDto(x)).collect(Collectors.toList());
  }

  @GetMapping("/{id}")
  public AlunoDto show(@PathVariable Long id) {
    return new AlunoDto(alunoService.find(id));
  }

  @PatchMapping("/{id}")
  public AlunoDto update(@RequestBody AlunoDto aluno, @PathVariable Long id) {
    return new AlunoDto(alunoService.update(id, aluno));
  }

  @DeleteMapping("/{id}")
  public String delete(@PathVariable Long id) {
    alunoService.delete(id);
    return "Deleted";
  }
}
