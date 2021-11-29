package br.uff.sispre.helpers;

public abstract class Sanitizer {
  public static String phoneNumber = "[()\\-\\s]";
  public static String cpf = "[\\.\\-]";
  public static String rg = "[\\.\\-]";

  public static String sanitize(String sanitizer, String value) {
    if (value == null)
      return null;
    return value.replaceAll(sanitizer, "");
  }
}
