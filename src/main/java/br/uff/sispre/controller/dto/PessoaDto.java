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
  public String nome;

  @JsonProperty
  public String endereco;

  @JsonProperty
  public String telefone;

  @JsonProperty
  public String email;

  @JsonIgnore
  public String senha;

  public PessoaDto(T pessoa) {
    this.id = pessoa.getId();
    this.cpf = pessoa.getCpf();
    this.rg = pessoa.getRg();
    this.nome = pessoa.getNome();
    this.endereco = pessoa.getEndereco();
    this.telefone = pessoa.getTelefone();
    this.email = pessoa.getEmail();
  }
}
