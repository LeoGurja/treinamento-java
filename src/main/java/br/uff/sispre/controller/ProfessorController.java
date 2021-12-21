package br.uff.sispre.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.uff.sispre.service.ProfessorService;

@Controller
@RequestMapping("/professores")
public class ProfessorController {
  @Autowired
  private ProfessorService professorService;

  @GetMapping
  public String listaProfessores() {
    return "professores/index";
  }
}
