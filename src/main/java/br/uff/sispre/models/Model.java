package br.uff.sispre.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class Model {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
}
