package br.uff.sispre.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Nota extends Model {
  @ManyToOne(fetch = FetchType.LAZY)
  private Aluno aluno;

  // TODO: confirmar regra de negócio para notas
  @ManyToOne(fetch = FetchType.LAZY)
  private Materia materia;

  @Column(name = "value")
  private Double value;
}
