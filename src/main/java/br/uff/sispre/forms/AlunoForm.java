package br.uff.sispre.forms;

import br.uff.sispre.helpers.Sanitizer;
import br.uff.sispre.helpers.Sha256;
import br.uff.sispre.models.Aluno;
import br.uff.sispre.params.AlunoParams;

public abstract class AlunoForm {
  public static void apply(Aluno aluno, AlunoParams params) {
    aluno.setName(params.name);
    aluno.setEmail(params.email);
    aluno.setCpf(Sanitizer.sanitizeCpf(params.cpf));
    aluno.setRg(Sanitizer.sanitizeRg(params.rg));
    aluno.setAddress(params.address);
    aluno.setPhoneNumber(Sanitizer.sanitizePhoneNumber(params.phoneNumber));
    aluno.setPasswordDigest(Sha256.encryptPassword(params.password));
  }
}
