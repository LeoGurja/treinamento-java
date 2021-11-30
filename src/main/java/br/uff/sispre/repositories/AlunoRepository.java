package br.uff.sispre.repositories;

import br.uff.sispre.models.Aluno;

public interface AlunoRepository extends PessoaRepository<Aluno> {
  Aluno findByCpf(String cpf);
}
