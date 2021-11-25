package br.uff.sispre.controllers.resources;

import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.uff.sispre.models.Aluno;
import br.uff.sispre.models.Nota;
import br.uff.sispre.models.Turma;

public class AlunoResource extends PessoaResource<Aluno> {
  @JsonProperty
  Long turma_id;

  @JsonProperty
  Set<Long> nota_ids;

  public AlunoResource(Aluno aluno) {
    super(aluno);
    Turma turma = aluno.getTurma();
    this.turma_id = turma != null ? turma.getId() : null;
    this.nota_ids = aluno.getNotas().stream().map(Nota::getId).collect(Collectors.toSet());
  }
}
