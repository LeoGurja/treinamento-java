package br.uff.sispre.models;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Aluno extends Pessoa {
  public Aluno() {
  }
}
