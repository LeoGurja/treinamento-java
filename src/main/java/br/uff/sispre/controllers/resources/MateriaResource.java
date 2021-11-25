package br.uff.sispre.controllers.resources;

import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.uff.sispre.models.Materia;
import br.uff.sispre.models.Nota;
import br.uff.sispre.models.Turma;

public class MateriaResource extends ModelResource<Materia> {
  @JsonProperty
  Set<Long> turmaIds;

  @JsonProperty
  Set<Long> notaIds;

  @JsonProperty
  String name;

  @JsonProperty
  String description;

  @JsonProperty
  Long professorId;

  public MateriaResource(Materia materia) {
    super(materia);

    this.name = materia.getName();
    this.description = materia.getDescription();

    this.professorId = materia.getProfessor().getId();
    this.turmaIds = materia.getTurmas().stream().map(Turma::getId).collect(Collectors.toSet());
    this.notaIds = materia.getNotas().stream().map(Nota::getId).collect(Collectors.toSet());
  }
}
