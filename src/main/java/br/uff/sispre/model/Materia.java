package br.uff.sispre.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Materia {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToMany(mappedBy = "materias")
  private List<Turma> turmas = new ArrayList<>();

  @OneToMany(mappedBy = "materia", cascade = CascadeType.ALL)
  @OnDelete(action = OnDeleteAction.CASCADE)
  private List<Nota> notas = new ArrayList<>();

  @Column(name = "name", nullable = false, unique = true)
  private String nome;

  @Column(name = "description", nullable = false)
  private String descricao;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "professor_id")
  private Professor professor;

  public Optional<Professor> getProfessor() {
    return Optional.ofNullable(this.professor);
  }

  public void setProfessor(Optional<Professor> professor) {
    this.professor = professor.orElse(null);
  }
}
