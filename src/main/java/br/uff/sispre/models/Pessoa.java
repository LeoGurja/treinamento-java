package br.uff.sispre.models;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@MappedSuperclass
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

  @Transient
  private transient String password;

  public String getCpf() {
    return this.cpf;
  }

  public String getRg() {
    return this.rg;
  }

  public String getName() {
    return this.name;
  }

  public String getAddress() {
    return this.address;
  }

  public String getPhoneNumber() {
    return this.phoneNumber;
  }

  public String getEmail() {
    return this.email;
  }

  public String getPasswordDigest() {
    return this.passwordDigest;
  }

  public String getPassword() {
    return this.password;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public void setRg(String rg) {
    this.rg = rg;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public void setPasswordDigest(String passwordDigest) {
    this.passwordDigest = passwordDigest;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
