package br.uff.sispre.controllers.resources;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.uff.sispre.models.Aluno;
import br.uff.sispre.models.Materia;
import br.uff.sispre.models.Turma;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TurmaResource extends ModelResource<Turma> {
  @JsonProperty
  public List<Long> materiaIds;

  @JsonProperty
  public List<Long> alunoIds;

  @JsonProperty
  public String name;

  public TurmaResource(Turma turma) {
    super(turma);

    this.name = turma.getName();
    this.materiaIds = turma.getMaterias().stream().map(Materia::getId).collect(Collectors.toList());
    this.alunoIds = turma.getAlunos().stream().map(Aluno::getId).collect(Collectors.toList());
  }
}
