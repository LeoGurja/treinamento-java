package br.uff.sispre.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Turma extends Model {
  @ManyToMany
  @JoinTable(name = "turma_materia", joinColumns = @JoinColumn(name = "turma_id"), inverseJoinColumns = @JoinColumn(name = "materia_id"))
  private Set<Materia> materias;

  @OneToMany(mappedBy = "turma")
  private Set<Aluno> alunos;

  @Column(name = "name", nullable = false, unique = true)
  private String name;

  @Transient
  private transient Set<Long> materiaIds;

  @Transient
  private transient Set<Long> alunoIds;

  public Set<Materia> getMaterias() {
    return this.materias;
  }

  public void setMaterias(Set<Materia> materias) {
    this.materias = materias;
  }

  public Set<Aluno> getAlunos() {
    return this.alunos;
  }

  public void setAlunos(Set<Aluno> alunos) {
    this.alunos = alunos;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<Long> getMateriaIds() {
    return this.materiaIds;
  }

  public void setMateriaIds(Set<Long> materiaIds) {
    this.materiaIds = materiaIds;
  }

  public Set<Long> getAlunoIds() {
    return this.alunoIds;
  }

  public void setAlunoIds(Set<Long> alunoIds) {
    this.alunoIds = alunoIds;
  }
}
