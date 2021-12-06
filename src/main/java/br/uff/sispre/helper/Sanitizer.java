package br.uff.sispre.helper;

import java.util.regex.Pattern;

public abstract class Sanitizer {
  public static Pattern phoneNumber = Pattern.compile("[()\\-]");
  public static Pattern cpf = Pattern.compile("[\\.\\-]");
  public static Pattern rg = Pattern.compile("[\\.\\-]");
  public static Pattern whitespace = Pattern.compile("\\s");

  public static String sanitize(Pattern sanitizer, String value) {
    if (value == null)
      return null;
    return sanitizer.matcher(value).replaceAll("");
  }
}
