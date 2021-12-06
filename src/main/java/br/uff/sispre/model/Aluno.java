package br.uff.sispre.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Aluno extends Pessoa {
  @ManyToOne
  @JoinColumn(name = "turma_id")
  private Turma turma;

  @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL)
  @OnDelete(action = OnDeleteAction.CASCADE)
  private List<Nota> notas = new ArrayList<>();

  public Optional<Turma> getTurma() {
    return Optional.ofNullable(this.turma);
  }

  public void setTurma(Optional<Turma> turma) {
    this.turma = turma.orElse(null);
  }
}
