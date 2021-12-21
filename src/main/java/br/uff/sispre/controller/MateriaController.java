package br.uff.sispre.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.uff.sispre.service.MateriaService;

@Controller
@RequestMapping("/materias")
public class MateriaController {
  @Autowired
  private MateriaService materiaService;

  @GetMapping
  public String listaMaterias() {
    return "materias/index";
  }
}
