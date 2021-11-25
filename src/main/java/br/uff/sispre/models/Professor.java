package br.uff.sispre.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class Professor extends Pessoa {
  @OneToOne(mappedBy = "professor", fetch = FetchType.LAZY)
  private Materia materia;

  @Transient
  private transient Long materiaId;

  public Materia getMateria() {
    return this.materia;
  }

  public void setMateria(Materia materia) {
    this.materia = materia;
  }

  public Long getMateriaId() {
    return this.materiaId;
  }

  public void setMateriaId(Long materiaId) {
    this.materiaId = materiaId;
  }
}
