package br.uff.sispre.controllers.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.uff.sispre.models.Materia;
import br.uff.sispre.models.Professor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ProfessorResource extends PessoaResource<Professor> {
  @JsonProperty
  public Long materiaId;

  public ProfessorResource(Professor professor) {
    super(professor);
    this.materiaId = professor.getMateria().map(Materia::getId).orElse(null);
  }
}
