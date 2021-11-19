package br.uff.sispre.forms;

import br.uff.sispre.helpers.Sanitizer;
import br.uff.sispre.helpers.Sha256;
import br.uff.sispre.models.Pessoa;
import br.uff.sispre.params.PessoaParams;

public abstract class PessoaForm {
  public static void apply(Pessoa pessoa, PessoaParams params) {
    pessoa.setName(params.name);
    pessoa.setEmail(params.email);
    pessoa.setCpf(Sanitizer.sanitizeCpf(params.cpf));
    pessoa.setRg(Sanitizer.sanitizeRg(params.rg));
    pessoa.setAddress(params.address);
    pessoa.setPhoneNumber(Sanitizer.sanitizePhoneNumber(params.phoneNumber));
    pessoa.setPasswordDigest(Sha256.encryptPassword(params.password));
  }
}
