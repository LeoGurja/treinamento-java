package br.uff.sispre.factories;

import br.uff.sispre.models.Turma;

public abstract class TurmaFactory {
  private static long nameCounter = 0;

  public static Turma build() {
    Turma turma = new Turma();
    turma.setName(String.format("Turma %d", nameCounter++));
    return turma;
  }
}
