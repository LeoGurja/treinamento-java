package br.uff.sispre.factory;

import br.uff.sispre.model.Nota;

public class NotaFactory {
  public static Nota build() {
    Nota nota = new Nota();
    nota.setAluno(AlunoFactory.build());
    nota.setMateria(MateriaFactory.build());
    nota.setValue(10.0);
    return nota;
  }
}
