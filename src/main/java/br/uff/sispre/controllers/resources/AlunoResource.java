package br.uff.sispre.controllers.resources;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.uff.sispre.models.Aluno;
import br.uff.sispre.models.Nota;
import br.uff.sispre.models.Turma;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AlunoResource extends PessoaResource<Aluno> {
  @JsonProperty
  public Long turmaId;

  @JsonProperty
  public List<Long> notaIds;

  public AlunoResource(Aluno aluno) {
    super(aluno);
    Turma turma = aluno.getTurma();
    this.turmaId = turma != null ? turma.getId() : null;
    this.notaIds = aluno.getNotas().stream().map(Nota::getId).collect(Collectors.toList());
  }
}
