package br.uff.sispre.factories;

import br.uff.sispre.models.Professor;

public abstract class ProfessorFactory {
  private static long cpfCounter = 100_000_000_00l;
  private static long rgCounter = 10_000_000_0;
  private static long nameCounter = 1;

  public static Professor build() {
    Professor professor = new Professor();
    professor.setCpf(String.format("%d", cpfCounter++));
    professor.setRg(String.format("%d", rgCounter++));
    professor.setName(String.format("Aluno %d", nameCounter++));
    professor.setAddress("Algum lugar");
    return professor;
  }
}
