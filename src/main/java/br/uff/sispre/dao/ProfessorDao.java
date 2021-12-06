package br.uff.sispre.dao;

import br.uff.sispre.model.Professor;

public interface ProfessorDao extends PessoaDao<Professor> {
  Professor findByCpf(String cpf);
}
