package br.uff.sispre.models;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Aluno extends Pessoa {
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "turma_id")
  private Turma turma;

  @OneToMany(mappedBy = "aluno")
  private Set<Nota> notas;
}
