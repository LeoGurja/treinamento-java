package br.uff.sispre.repositories;

import org.springframework.data.repository.CrudRepository;

import br.uff.sispre.models.Model;

public interface ModelRepository<T extends Model> extends CrudRepository<T, Long> {

}
