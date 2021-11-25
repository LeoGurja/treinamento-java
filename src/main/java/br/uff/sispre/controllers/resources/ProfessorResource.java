package br.uff.sispre.controllers.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.uff.sispre.models.Materia;
import br.uff.sispre.models.Professor;

public class ProfessorResource extends PessoaResource<Professor> {
  @JsonProperty
  Long materiaId;

  public ProfessorResource(Professor professor) {
    super(professor);
    Materia materia = professor.getMateria();
    this.materiaId = materia != null ? materia.getId() : null;
  }
}
