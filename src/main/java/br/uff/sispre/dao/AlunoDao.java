package br.uff.sispre.dao;

import br.uff.sispre.model.Aluno;

public interface AlunoDao extends PessoaDao<Aluno> {
  Aluno findByCpf(String cpf);
}
