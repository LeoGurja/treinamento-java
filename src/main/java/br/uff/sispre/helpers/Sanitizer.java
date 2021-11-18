package br.uff.sispre.helpers;

public abstract class Sanitizer {
  public static String sanitizePhoneNumber(String phoneNumber) {
    return phoneNumber.replaceAll("[\\(\\)-]", "");
  }

  public static String sanitizeCpf(String cpf) {
    return cpf.replaceAll("[\\.\\-\s]", "");
  }

  public static String sanitizeRg(String rg) {
    return rg.replaceAll("[\\.\\-]", "");
  }
}
