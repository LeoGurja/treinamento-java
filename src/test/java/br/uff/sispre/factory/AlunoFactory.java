package br.uff.sispre.factory;

import br.uff.sispre.model.Aluno;

public abstract class AlunoFactory {
  private static long cpfCounter = 100_000_000_00l;
  private static long rgCounter = 10_000_000_0;
  private static long nameCounter = 1;

  public static Aluno build() {
    Aluno aluno = new Aluno();
    aluno.setCpf(String.format("%d", cpfCounter++));
    aluno.setRg(String.format("%d", rgCounter++));
    aluno.setNome(String.format("Aluno %d", nameCounter++));
    aluno.setEndereco("Algum lugar");
    return aluno;
  }
}
