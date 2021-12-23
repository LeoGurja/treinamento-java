package br.uff.sispre.controller.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.uff.sispre.model.Nota;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NotaDto {
  @JsonProperty
  public Long id;

  @JsonProperty
  @NotEmpty
  public Long alunoId;

  @JsonProperty
  @NotEmpty
  public Long materiaId;

  @JsonProperty
  @PositiveOrZero
  @Max(value = 10)
  public Double nota;

  public NotaDto(Nota nota) {
    this.nota = nota.getNota();

    this.alunoId = nota.getAluno().getId();
    this.materiaId = nota.getMateria().getId();
  }
}
