package br.uff.sispre.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

  @OneToOne(mappedBy = "professor_id", fetch = FetchType.LAZY)
  private Professor professor;
}
