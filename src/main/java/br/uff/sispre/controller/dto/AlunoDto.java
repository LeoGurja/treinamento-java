package br.uff.sispre.controller.dto;

import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.uff.sispre.model.Aluno;
import br.uff.sispre.model.Nota;
import br.uff.sispre.model.Turma;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AlunoDto extends PessoaDto<Aluno> {
  @JsonProperty
  public Long turmaId;

  @JsonProperty
  public Set<Long> notaIds;

  public AlunoDto(Aluno aluno) {
    super(aluno);
    this.turmaId = aluno.getTurma().map(Turma::getId).orElse(null);
    this.notaIds = aluno.getNotas().stream().map(Nota::getId).collect(Collectors.toSet());
  }
}
