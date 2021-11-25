package br.uff.sispre.controllers.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.uff.sispre.models.Pessoa;

public abstract class PessoaResource<T extends Pessoa> extends ModelResource<Pessoa> {
  @JsonProperty
  String cpf;

  @JsonProperty
  String rg;

  @JsonProperty
  String name;

  @JsonProperty
  String address;

  @JsonProperty
  String phoneNumber;

  @JsonProperty
  String email;

  public PessoaResource(T pessoa) {
    super(pessoa);

    this.cpf = pessoa.getCpf();
    this.rg = pessoa.getRg();
    this.name = pessoa.getName();
    this.address = pessoa.getAddress();
    this.phoneNumber = pessoa.getPhoneNumber();
    this.email = pessoa.getPhoneNumber();
  }
}
