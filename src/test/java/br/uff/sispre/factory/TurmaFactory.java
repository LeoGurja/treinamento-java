package br.uff.sispre.factory;

import br.uff.sispre.model.Turma;

public abstract class TurmaFactory {
  private static long nameCounter = 0;

  public static Turma build() {
    Turma turma = new Turma();
    turma.setNome(String.format("Turma %d", nameCounter++));
    return turma;
  }
}
