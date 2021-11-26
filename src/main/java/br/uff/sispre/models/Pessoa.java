package br.uff.sispre.models;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class Pessoa extends Model {
  @Column(name = "cpf", nullable = false, length = 11, unique = true)
  private String cpf;

  @Column(name = "rg", nullable = false, length = 9, unique = true)
  private String rg;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "address", nullable = false)
  private String address;

  @Column(name = "phone_number")
  private String phoneNumber;

  @Column(name = "email")
  private String email;

  @Column(name = "password_digest")
  private String passwordDigest;
}
