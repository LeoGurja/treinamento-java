package br.uff.sispre.controller.dto;

import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.uff.sispre.model.Materia;
import br.uff.sispre.model.Nota;
import br.uff.sispre.model.Professor;
import br.uff.sispre.model.Turma;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MateriaDto {
  @JsonProperty
  public Long id;

  @JsonProperty
  public Set<Long> turmaIds;

  @JsonProperty
  public Set<Long> notaIds;

  @JsonProperty
  public String name;

  @JsonProperty
  public String description;

  @JsonProperty
  public Long professorId;

  public MateriaDto(Materia materia) {
    this.name = materia.getName();
    this.description = materia.getDescription();

    this.professorId = materia.getProfessor().map(Professor::getId).orElse(null);
    this.turmaIds = materia.getTurmas().stream().map(Turma::getId).collect(Collectors.toSet());
    this.notaIds = materia.getNotas().stream().map(Nota::getId).collect(Collectors.toSet());
  }
}
