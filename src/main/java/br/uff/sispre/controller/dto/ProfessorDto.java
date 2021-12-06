package br.uff.sispre.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.uff.sispre.model.Materia;
import br.uff.sispre.model.Professor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ProfessorDto extends PessoaDto<Professor> {
  @JsonProperty
  public Long materiaId;

  public ProfessorDto(Professor professor) {
    super(professor);
    this.materiaId = professor.getMateria().map(Materia::getId).orElse(null);
  }
}
