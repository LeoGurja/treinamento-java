package br.uff.sispre.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.uff.sispre.model.Nota;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NotaDto {
  @JsonProperty
  public Long id;

  @JsonProperty
  public Long alunoId;

  @JsonProperty
  public Long materiaId;

  @JsonProperty
  public Double value;

  public NotaDto(Nota nota) {
    this.value = nota.getValue();

    this.alunoId = nota.getAluno().getId();
    this.materiaId = nota.getMateria().getId();
  }
}
