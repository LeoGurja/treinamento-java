package br.uff.sispre.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Turma extends Model {
  @ManyToMany
  @JoinTable(name = "turma_materia", joinColumns = @JoinColumn(name = "turma_id"), inverseJoinColumns = @JoinColumn(name = "materia_id"))
  private List<Materia> materias;

  @OneToMany(mappedBy = "turma")
  private List<Aluno> alunos;

  @Column(name = "name", nullable = false, unique = true)
  private String name;

  public List<Materia> getMaterias() {
    return this.materias != null ? this.materias : new ArrayList<>();
  }

  public List<Aluno> getAlunos() {
    return this.alunos != null ? this.alunos : new ArrayList<>();
  }
}
