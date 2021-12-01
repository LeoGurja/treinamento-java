package br.uff.sispre.factories;

import br.uff.sispre.models.Aluno;

public abstract class AlunoFactory {
  private static long cpfCounter = 100_000_000_00l;
  private static long rgCounter = 10_000_000_0;
  private static long nameCounter = 1;

  public static Aluno build() {
    Aluno aluno = new Aluno();
    aluno.setCpf(String.format("%d", cpfCounter++));
    aluno.setRg(String.format("%d", rgCounter++));
    aluno.setName(String.format("Aluno %d", nameCounter++));
    aluno.setAddress("Algum lugar");
    return aluno;
  }
}
