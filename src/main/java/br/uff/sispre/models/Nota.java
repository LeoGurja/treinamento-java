package br.uff.sispre.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Nota extends Model {
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "aluno_id")
  private Aluno aluno;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "materia_id")
  private Materia materia;

  @Column(name = "value")
  private Double value;
}
