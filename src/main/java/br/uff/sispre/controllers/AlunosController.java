package br.uff.sispre.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.uff.sispre.forms.AlunoForm;
import br.uff.sispre.models.Aluno;
import br.uff.sispre.params.AlunoParams;
import br.uff.sispre.repositories.AlunoRepository;

@RestController
@RequestMapping(path = "/alunos")
public class AlunosController {

  @Autowired
  private AlunoRepository alunoRepository;

  @PostMapping
  public String create(@RequestBody AlunoParams alunoParams) {
    Aluno aluno = new Aluno();
    AlunoForm.apply(aluno, alunoParams);
    alunoRepository.save(aluno);
    return "Saved";
  }

  @GetMapping
  public Iterable<Aluno> index() {
    return alunoRepository.findAll();
  }
}
