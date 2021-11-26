package br.uff.sispre.controllers.resources;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.uff.sispre.models.Materia;
import br.uff.sispre.models.Nota;
import br.uff.sispre.models.Turma;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MateriaResource extends ModelResource<Materia> {
  @JsonProperty
  public List<Long> turmaIds;

  @JsonProperty
  public List<Long> notaIds;

  @JsonProperty
  public String name;

  @JsonProperty
  public String description;

  @JsonProperty
  public Long professorId;

  public MateriaResource(Materia materia) {
    super(materia);

    this.name = materia.getName();
    this.description = materia.getDescription();

    this.professorId = materia.getProfessor().getId();
    this.turmaIds = materia.getTurmas().stream().map(Turma::getId).collect(Collectors.toList());
    this.notaIds = materia.getNotas().stream().map(Nota::getId).collect(Collectors.toList());
  }
}
