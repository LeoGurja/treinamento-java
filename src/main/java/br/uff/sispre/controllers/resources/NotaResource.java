package br.uff.sispre.controllers.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.uff.sispre.models.Nota;

public class NotaResource extends ModelResource<Nota> {
  @JsonProperty
  Long alunoId;

  @JsonProperty
  Long materiaId;

  @JsonProperty
  Double value;

  public NotaResource(Nota nota) {
    super(nota);

    this.value = nota.getValue();

    this.alunoId = nota.getAluno().getId();
    this.materiaId = nota.getMateria().getId();
  }
}
