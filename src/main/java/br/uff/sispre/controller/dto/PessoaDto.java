package br.uff.sispre.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.uff.sispre.model.Pessoa;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public abstract class PessoaDto<T extends Pessoa> {
  @JsonProperty
  public Long id;

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

  public PessoaDto(T pessoa) {
    this.id = pessoa.getId();
    this.cpf = pessoa.getCpf();
    this.rg = pessoa.getRg();
    this.name = pessoa.getName();
    this.address = pessoa.getAddress();
    this.phoneNumber = pessoa.getPhoneNumber();
    this.email = pessoa.getEmail();
  }
}
