package br.uff.sispre.dao;

import org.springframework.data.repository.CrudRepository;

import br.uff.sispre.model.Pessoa;

public interface PessoaDao<T extends Pessoa> extends CrudRepository<T, Long> {

}
