package br.uff.sispre.controllers.resources;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.uff.sispre.models.Pessoa;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public abstract class PessoaResource<T extends Pessoa> extends ModelResource<Pessoa> {
  @JsonProperty
  public String cpf;

  @JsonProperty
  public String rg;

  @JsonProperty
  public String name;

  @JsonProperty
  public String address;

  @JsonProperty
  public String phoneNumber;

  @JsonProperty
  public String email;

  @JsonIgnore
  public String password;

  public PessoaResource(T pessoa) {
    super(pessoa);

    this.cpf = pessoa.getCpf();
    this.rg = pessoa.getRg();
    this.name = pessoa.getName();
    this.address = pessoa.getAddress();
    this.phoneNumber = pessoa.getPhoneNumber();
    this.email = pessoa.getEmail();
  }
}
