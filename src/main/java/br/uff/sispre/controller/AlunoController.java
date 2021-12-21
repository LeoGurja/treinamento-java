package br.uff.sispre.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import br.uff.sispre.service.AlunoService;

@Controller
@RequestMapping("/alunos")
public class AlunoController {
  @Autowired
  private AlunoService alunoService;

  @GetMapping("/")
  public String listaAlunos(Model model) {
    model.addAttribute("alunos", alunoService.listaAlunos());
    return "alunos/index";
  }

  @GetMapping("/{id}")
  public String mostraAluno(@PathVariable Long id, Model model) {
    model.addAttribute("aluno", alunoService.porId(id));
    return "alunos/show";
  }
}
