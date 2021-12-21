package br.uff.sispre.controller.api;

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

import br.uff.sispre.controller.dto.NotaDto;
import br.uff.sispre.service.NotaService;

@RestController
@RequestMapping("/api/notas")
public class NotaApiController {
  @Autowired
  private NotaService notaService;

  @PostMapping
  public NotaDto criaNota(@RequestBody NotaDto params) {
    return new NotaDto(notaService.criaNota(params));
  }

  @GetMapping
  public List<NotaDto> listaNotas() {
    return notaService.listaNotas().stream().map(nota -> new NotaDto(nota)).collect(Collectors.toList());
  }

  @GetMapping("/{id}")
  public NotaDto mostraNota(@PathVariable Long id) {
    return new NotaDto(notaService.porId(id));
  }

  @PatchMapping("/{id}")
  public NotaDto alteraNota(@RequestBody NotaDto params, @PathVariable Long id) {
    return new NotaDto(notaService.criaNota(params));
  }

  @DeleteMapping("/{id}")
  public String deletaNota(@PathVariable Long id) {
    notaService.deletaNota(id);
    return "Deleted";
  }
}
