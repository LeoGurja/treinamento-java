package br.uff.sispre.controllers.resources;

import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.uff.sispre.models.Aluno;
import br.uff.sispre.models.Materia;
import br.uff.sispre.models.Turma;

public class TurmaResource extends ModelResource<Turma> {
  @JsonProperty
  Set<Long> materiaIds;

  @JsonProperty
  Set<Long> alunoIds;

  @JsonProperty
  String name;

  public TurmaResource(Turma turma) {
    super(turma);

    this.name = turma.getName();
    this.materiaIds = turma.getMaterias().stream().map(Materia::getId).collect(Collectors.toSet());
    this.alunoIds = turma.getAlunos().stream().map(Aluno::getId).collect(Collectors.toSet());
  }
}
