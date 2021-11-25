package br.uff.sispre.models;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Aluno extends Pessoa {
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "turma_id")
  private Turma turma;

  @OneToMany(mappedBy = "aluno")
  private Set<Nota> notas;

  @Transient
  private transient Long turmaId;

  public Turma getTurma() {
    return this.turma;
  }

  public void setTurma(Turma turma) {
    this.turma = turma;
  }

  public Set<Nota> getNotas() {
    return this.notas;
  }

  public void setNotas(Set<Nota> notas) {
    this.notas = notas;
  }

  public Long getTurmaId() {
    return this.turmaId;
  }

  public void setTurmaId(Long turmaId) {
    this.turmaId = turmaId;
  }

}
