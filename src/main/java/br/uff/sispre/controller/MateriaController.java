package br.uff.sispre.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/materias")
public class MateriaController {
  @GetMapping(path = "/")
  public String index() {
    return "materias/index";
  }
}
