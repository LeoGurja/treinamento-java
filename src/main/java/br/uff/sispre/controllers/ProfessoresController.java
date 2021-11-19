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
import br.uff.sispre.models.Professor;
import br.uff.sispre.params.PessoaParams;
import br.uff.sispre.repositories.ProfessorRepository;

@RestController
@RequestMapping(path = "/professores")
public class ProfessoresController {
  @Autowired
  private ProfessorRepository ProfessorRepository;

  @PostMapping
  public String create(@RequestBody PessoaParams params) {
    Professor Professor = new Professor();
    PessoaForm.apply(Professor, params);
    ProfessorRepository.save(Professor);
    return "Saved";
  }

  @GetMapping
  public Iterable<Professor> index() {
    return ProfessorRepository.findAll();
  }

  @GetMapping(path = "/{id}")
  public Professor show(@PathVariable Long id) {
    return ProfessorRepository.findById(id).get();
  }

  @PatchMapping(path = "/{id}")
  public String update(@RequestBody PessoaParams params, @PathVariable Long id) {
    Professor Professor = ProfessorRepository.findById(id).get();
    PessoaForm.apply(Professor, params);
    ProfessorRepository.save(Professor);
    return "Updated";
  }

  @DeleteMapping(path = "/{id}")
  public String delete(@PathVariable Long id) {
    Professor Professor = ProfessorRepository.findById(id).get();
    ProfessorRepository.delete(Professor);
    return "Deleted";
  }
}
