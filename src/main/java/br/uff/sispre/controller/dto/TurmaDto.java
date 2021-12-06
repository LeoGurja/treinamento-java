package br.uff.sispre.controller.dto;

import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.uff.sispre.model.Aluno;
import br.uff.sispre.model.Materia;
import br.uff.sispre.model.Turma;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TurmaDto {
  @JsonProperty
  public Long id;

  @JsonProperty
  public Set<Long> materiaIds;

  @JsonProperty
  public Set<Long> alunoIds;

  @JsonProperty
  public String name;

  public TurmaDto(Turma turma) {
    this.name = turma.getName();
    this.materiaIds = turma.getMaterias().stream().map(Materia::getId).collect(Collectors.toSet());
    this.alunoIds = turma.getAlunos().stream().map(Aluno::getId).collect(Collectors.toSet());
  }
}
