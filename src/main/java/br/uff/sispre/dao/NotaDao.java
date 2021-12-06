package br.uff.sispre.dao;

import org.springframework.data.repository.CrudRepository;

import br.uff.sispre.model.Nota;

public interface NotaDao extends CrudRepository<Nota, Long> {

}
