package br.uff.sispre.model;

import java.util.Optional;

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

  public Optional<Materia> getMateria() {
    return Optional.ofNullable(this.materia);
  }

  public void setMateria(Optional<Materia> materia) {
    this.materia = materia.orElse(null);
  }
}
