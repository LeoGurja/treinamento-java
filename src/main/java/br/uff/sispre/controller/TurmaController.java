package br.uff.sispre.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.uff.sispre.service.TurmaService;

@Controller
@RequestMapping("/turmas")
public class TurmaController {
  @Autowired
  private TurmaService turmaService;

  @GetMapping
  public String listaTurmas() {
    return "turmas/index";
  }
}
