package br.uff.sispre.controllers.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.uff.sispre.models.Model;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public abstract class ModelResource<T extends Model> {
  @JsonProperty
  public Long id;

  public ModelResource(T model) {
    this.id = model.getId();
  }
}
