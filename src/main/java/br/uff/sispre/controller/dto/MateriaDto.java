package br.uff.sispre.controller.dto;

import java.util.List;
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
  public List<Long> turmaIds;

  @JsonProperty
  public List<Long> notaIds;

  @JsonProperty
  public String nome;

  @JsonProperty
  public String descricao;

  @JsonProperty
  public Long professorId;

  public MateriaDto(Materia materia) {
    this.nome = materia.getNome();
    this.descricao = materia.getDescricao();

    this.professorId = materia.getProfessor().map(Professor::getId).orElse(null);
    this.turmaIds = materia.getTurmas().stream().map(Turma::getId).collect(Collectors.toList());
    this.notaIds = materia.getNotas().stream().map(Nota::getId).collect(Collectors.toList());
  }
}
