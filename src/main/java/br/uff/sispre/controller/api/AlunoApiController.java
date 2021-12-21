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
  public AlunoDto criaAluno(@RequestBody AlunoDto params) {
    return new AlunoDto(alunoService.criaAluno(params));
  }

  @GetMapping
  public List<AlunoDto> listaAlunos() {
    return alunoService.listaAlunos().stream().map(x -> new AlunoDto(x)).collect(Collectors.toList());
  }

  @GetMapping("/{id}")
  public AlunoDto mostraAluno(@PathVariable Long id) {
    return new AlunoDto(alunoService.porId(id));
  }

  @PatchMapping("/{id}")
  public AlunoDto alteraAluno(@RequestBody AlunoDto aluno, @PathVariable Long id) {
    return new AlunoDto(alunoService.alteraAluno(id, aluno));
  }

  @DeleteMapping("/{id}")
  public String deletaAluno(@PathVariable Long id) {
    alunoService.deletaAluno(id);
    return "Deleted";
  }
}
