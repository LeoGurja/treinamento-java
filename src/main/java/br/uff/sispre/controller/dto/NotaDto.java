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
  public Double nota;

  public NotaDto(Nota nota) {
    this.nota = nota.getNota();

    this.alunoId = nota.getAluno().getId();
    this.materiaId = nota.getMateria().getId();
  }
}
