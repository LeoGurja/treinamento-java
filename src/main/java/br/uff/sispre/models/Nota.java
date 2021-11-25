package br.uff.sispre.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class Nota extends Model {
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "aluno_id")
  private Aluno aluno;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "materia_id")
  private Materia materia;

  @Column(name = "value")
  private Double value;

  @Transient
  private transient Long alunoId;

  @Transient
  private transient Long materiaId;

  public Aluno getAluno() {
    return this.aluno;
  }

  public void setAluno(Aluno aluno) {
    this.aluno = aluno;
  }

  public Materia getMateria() {
    return this.materia;
  }

  public void setMateria(Materia materia) {
    this.materia = materia;
  }

  public Double getValue() {
    return this.value;
  }

  public void setValue(Double value) {
    this.value = value;
  }

  public Long getAlunoId() {
    return this.alunoId;
  }

  public void setAlunoId(Long alunoId) {
    this.alunoId = alunoId;
  }

  public Long getMateriaId() {
    return this.materiaId;
  }

  public void setMateriaId(Long materiaId) {
    this.materiaId = materiaId;
  }

}
