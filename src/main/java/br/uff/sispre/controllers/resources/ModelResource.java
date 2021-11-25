package br.uff.sispre.controllers.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.uff.sispre.models.Model;

public abstract class ModelResource<T extends Model> {
  @JsonProperty
  Long id;

  public ModelResource(T model) {
    this.id = model.getId();
  }
}
