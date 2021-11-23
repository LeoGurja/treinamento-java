package br.uff.sispre.models;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Professor extends Pessoa {
  public Professor() {
  }
}
