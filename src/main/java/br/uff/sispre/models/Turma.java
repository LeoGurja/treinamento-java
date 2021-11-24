package br.uff.sispre.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Turma extends Model {
  @ManyToMany
  private List<Materia> materias;

  @OneToMany
  private List<Aluno> alunos;

  @Column(name = "name", nullable = false, unique = true)
  private String name;
}
