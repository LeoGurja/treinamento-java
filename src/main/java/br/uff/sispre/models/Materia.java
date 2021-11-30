package br.uff.sispre.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Materia extends Model {
  @ManyToMany(mappedBy = "materias")
  private List<Turma> turmas;

  @OneToMany(mappedBy = "materia", cascade = CascadeType.ALL)
  @OnDelete(action = OnDeleteAction.CASCADE)
  private List<Nota> notas;

  @Column(name = "name", nullable = false, unique = true)
  private String name;

  @Column(name = "description", nullable = false)
  private String description;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "professor_id")
  private Professor professor;

  public List<Nota> getNotas() {
    return this.notas != null ? this.notas : new ArrayList<>();
  }

  public List<Turma> getTurmas() {
    return this.turmas != null ? this.turmas : new ArrayList<>();
  }

  public Optional<Professor> getProfessor() {
    return Optional.ofNullable(this.professor);
  }

  public void setProfessor(Optional<Professor> professor) {
    this.professor = professor.orElse(null);
  }
}
