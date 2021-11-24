package br.uff.sispre.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Aluno extends Pessoa {
  @ManyToOne(fetch = FetchType.LAZY)
  private Turma turma;

  @OneToMany
  private List<Nota> notas;
}
