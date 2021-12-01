package br.uff.sispre.factories;

import br.uff.sispre.models.Nota;

public class NotaFactory {
  public static Nota build() {
    Nota nota = new Nota();
    nota.setAluno(AlunoFactory.build());
    nota.setMateria(MateriaFactory.build());
    nota.setValue(10.0);
    return nota;
  }
}
