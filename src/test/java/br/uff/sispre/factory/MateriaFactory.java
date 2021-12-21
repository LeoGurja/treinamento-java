package br.uff.sispre.factory;

import br.uff.sispre.model.Materia;

public abstract class MateriaFactory {
  private static long nameCounter = 0;

  public static Materia build() {
    Materia materia = new Materia();
    materia.setNome(String.format("calculo %d", nameCounter++));
    materia.setDescricao("Realizar muitos calculos sem motivo aparente...");
    return materia;
  }
}
