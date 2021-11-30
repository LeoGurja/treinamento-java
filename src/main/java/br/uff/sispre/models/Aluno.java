package br.uff.sispre.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
  private List<Nota> notas;

  public List<Nota> getNotas() {
    return this.notas != null ? this.notas : new ArrayList<>();
  }

  public Optional<Turma> getTurma() {
    return Optional.ofNullable(this.turma);
  }

  public void setTurma(Optional<Turma> turma) {
    this.turma = turma.orElse(null);
  }
}
