package br.uff.sispre.controller.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.hibernate.validator.constraints.br.CPF;

import br.uff.sispre.model.Pessoa;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public abstract class PessoaDto<T extends Pessoa> {
  @JsonProperty
  public Long id;

  @JsonProperty
  @NotEmpty
  @CPF
  public String cpf;

  @JsonProperty
  @NotEmpty
  @Size(min = 9, max = 12)
  public String rg;

  @JsonProperty
  @NotEmpty
  public String nome;

  @JsonProperty
  @NotEmpty
  public String endereco;

  @JsonProperty
  @NotEmpty
  public String telefone;

  @JsonProperty
  @NotEmpty
  @Email
  public String email;

  @JsonIgnore
  @NotEmpty
  @Size(min = 8)
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
