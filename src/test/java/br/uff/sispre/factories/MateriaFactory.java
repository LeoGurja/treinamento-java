package br.uff.sispre.factories;

import br.uff.sispre.models.Materia;

public abstract class MateriaFactory {
  private static long nameCounter = 0;

  public static Materia build() {
    Materia materia = new Materia();
    materia.setName(String.format("matéria %d", nameCounter++));
    materia.setDescription("descrição");
    return materia;
  }
}
