package br.uff.sispre.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.uff.sispre.forms.PessoaForm;
import br.uff.sispre.models.Aluno;
import br.uff.sispre.params.PessoaParams;
import br.uff.sispre.repositories.AlunoRepository;

@RestController
@RequestMapping(path = "/alunos")
public class AlunosController {
  @Autowired
  private AlunoRepository alunoRepository;

  @PostMapping
  public String create(@RequestBody PessoaParams params) {
    Aluno aluno = new Aluno();
    PessoaForm.apply(aluno, params);
    alunoRepository.save(aluno);
    return "Saved";
  }

  @GetMapping
  public Iterable<Aluno> index() {
    return alunoRepository.findAll();
  }

  @GetMapping(path = "/{id}")
  public Aluno show(@PathVariable Long id) {
    return alunoRepository.findById(id).get();
  }

  @PatchMapping(path = "/{id}")
  public String update(@RequestBody PessoaParams params, @PathVariable Long id) {
    Aluno aluno = alunoRepository.findById(id).get();
    PessoaForm.apply(aluno, params);
    alunoRepository.save(aluno);
    return "Updated";
  }

  @DeleteMapping(path = "/{id}")
  public String delete(@PathVariable Long id) {
    Aluno aluno = alunoRepository.findById(id).get();
    alunoRepository.delete(aluno);
    return "Deleted";
  }
}