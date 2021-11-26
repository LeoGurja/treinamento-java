package br.uff.sispre.controllers;

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

import br.uff.sispre.controllers.resources.NotaResource;
import br.uff.sispre.services.NotaService;

@RestController
@RequestMapping(path = "/notas")
public class NotasController {
  @Autowired
  private NotaService notaService;

  @PostMapping
  public NotaResource create(@RequestBody NotaResource params) {
    return new NotaResource(notaService.create(params));
  }

  @GetMapping
  public List<NotaResource> index() {
    return notaService.all().stream().map(nota -> new NotaResource(nota)).collect(Collectors.toList());
  }

  @GetMapping(path = "/{id}")
  public NotaResource show(@PathVariable Long id) {
    return new NotaResource(notaService.find(id));
  }

  @PatchMapping(path = "/{id}")
  public NotaResource update(@RequestBody NotaResource params, @PathVariable Long id) {
    return new NotaResource(notaService.create(params));
  }

  @DeleteMapping(path = "/{id}")
  public String delete(@PathVariable Long id) {
    notaService.delete(id);
    return "Deleted";
  }
}
