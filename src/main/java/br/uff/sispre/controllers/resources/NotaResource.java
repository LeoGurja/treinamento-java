package br.uff.sispre.controllers.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.uff.sispre.models.Nota;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NotaResource extends ModelResource<Nota> {
  @JsonProperty
  public Long alunoId;

  @JsonProperty
  public Long materiaId;

  @JsonProperty
  public Double value;

  public NotaResource(Nota nota) {
    super(nota);

    this.value = nota.getValue();

    this.alunoId = nota.getAluno().getId();
    this.materiaId = nota.getMateria().getId();
  }
}
