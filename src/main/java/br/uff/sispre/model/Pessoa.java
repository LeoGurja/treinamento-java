package br.uff.sispre.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class Pessoa {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "cpf", nullable = false, length = 11, unique = true)
  private String cpf;

  @Column(name = "rg", nullable = false, length = 9, unique = true)
  private String rg;

  @Column(name = "name", nullable = false)
  private String nome;

  @Column(name = "address", nullable = false)
  private String endereco;

  @Column(name = "phone_number")
  private String telefone;

  @Column(name = "email")
  private String email;

  @Column(name = "password_digest")
  private String hashSenha;
}
