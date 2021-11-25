package br.uff.sispre.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class Materia extends Model {
  @ManyToMany(mappedBy = "materias")
  private Set<Turma> turmas;

  @OneToMany(mappedBy = "materia")
  private Set<Nota> notas;

  @Column(name = "name", nullable = false, unique = true)
  private String name;

  @Column(name = "description", nullable = false)
  private String description;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "professor_id")
  private Professor professor;

  @Transient
  private transient Long professorId;

  @Transient
  private transient Set<Long> turmaIds;

  public Set<Turma> getTurmas() {
    return this.turmas;
  }

  public void setTurmas(Set<Turma> turmas) {
    this.turmas = turmas;
  }

  public Set<Nota> getNotas() {
    return this.notas;
  }

  public void setNotas(Set<Nota> notas) {
    this.notas = notas;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Professor getProfessor() {
    return this.professor;
  }

  public void setProfessor(Professor professor) {
    this.professor = professor;
  }

  public Long getProfessorId() {
    return this.professorId;
  }

  public void setProfessorId(Long professorId) {
    this.professorId = professorId;
  }

  public Set<Long> getTurmaIds() {
    return this.turmaIds;
  }

  public void setTurmaIds(Set<Long> turmaIds) {
    this.turmaIds = turmaIds;
  }
}
