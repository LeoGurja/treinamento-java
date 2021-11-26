package br.uff.sispre.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Professor extends Pessoa {
  @OneToOne(mappedBy = "professor", fetch = FetchType.LAZY)
  private Materia materia;
}
