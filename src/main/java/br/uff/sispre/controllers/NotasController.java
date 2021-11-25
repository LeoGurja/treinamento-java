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

import br.uff.sispre.models.Nota;
import br.uff.sispre.services.NotaService;

@RestController
@RequestMapping(path = "/notas")
public class NotasController {
  @Autowired
  private NotaService notaService;

  @PostMapping
  public Nota create(@RequestBody Nota nota) {
    notaService.create(nota);
    return nota;
  }

  @GetMapping
  public Iterable<Nota> index() {
    return notaService.all();
  }

  @GetMapping(path = "/{id}")
  public Nota show(@PathVariable Long id) {
    return notaService.find(id);
  }

  @PatchMapping(path = "/{id}")
  public Nota update(@RequestBody Nota nota, @PathVariable Long id) {
    notaService.update(id, nota);
    return nota;
  }

  @DeleteMapping(path = "/{id}")
  public String delete(@PathVariable Long id) {
    notaService.delete(id);
    return "Deleted";
  }
}
