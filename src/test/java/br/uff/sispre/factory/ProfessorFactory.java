package br.uff.sispre.factory;

import br.uff.sispre.model.Professor;

public abstract class ProfessorFactory {
  private static long cpfCounter = 100_000_000_00l;
  private static long rgCounter = 10_000_000_0;
  private static long nameCounter = 1;

  public static Professor build() {
    Professor professor = new Professor();
    professor.setCpf(String.format("%d", cpfCounter++));
    professor.setRg(String.format("%d", rgCounter++));
    professor.setNome(String.format("Professor %d", nameCounter++));
    professor.setEndereco("Algum lugar");
    return professor;
  }
}
