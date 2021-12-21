package br.uff.sispre.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Nota {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = { CascadeType.PERSIST })
  @JoinColumn(name = "aluno_id")
  private Aluno aluno;

  @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = { CascadeType.PERSIST })
  @JoinColumn(name = "materia_id")
  private Materia materia;

  @Column(name = "value")
  private Double nota;
}
