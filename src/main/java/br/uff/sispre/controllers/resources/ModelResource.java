package br.uff.sispre.controllers.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.uff.sispre.models.Model;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public abstract class ModelResource<T extends Model> {
  // renomeado para impedir que um id seja enviado diretamente ao model
  @JsonProperty
  public Long _id;

  public ModelResource(T model) {
    this._id = model.getId();
  }
}
