package br.uff.sispre.helpers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SanitizerTest {
  @Test
  void sanitizaCpf() {
    String cpf = "123.456.789-00";
    assertEquals(Sanitizer.sanitize(Sanitizer.cpf, cpf), "12345678900");
  }

  @Test
  void sanitizaRg() {
    String rg = "12.345.678-9";
    assertEquals(Sanitizer.sanitize(Sanitizer.rg, rg), "123456789");
  }

  @Test
  void sanitizaNumeroDeTelefone() {
    String phoneNumber = "(12)34567-8901";
    assertEquals(Sanitizer.sanitize(Sanitizer.phoneNumber, phoneNumber), "12345678901");
  }

  @Test
  void sanitizaEspacosEmBranco() {
    String phoneNumber = "(21) 99999 9999";
    assertEquals(Sanitizer.sanitize(Sanitizer.whitespace, phoneNumber), "(21)999999999");
  }
}
