package br.uff.sispre.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Professor extends Pessoa {
  @OneToOne(fetch = FetchType.LAZY)
  private Materia materia;
}
