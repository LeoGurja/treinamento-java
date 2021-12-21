package br.uff.sispre.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.uff.sispre.controller.dto.AlunoDto;
import br.uff.sispre.dao.AlunoDao;
import br.uff.sispre.dao.TurmaDao;
import br.uff.sispre.helper.Sanitizer;
import br.uff.sispre.helper.Sha256;
import br.uff.sispre.model.Aluno;

@Service
public class AlunoService {
  @Autowired
  private AlunoDao alunoDao;

  @Autowired
  private TurmaDao turmaDao;

  private Aluno aluno;

  public Aluno porId(Long id) {
    return alunoDao.findById(id).get();
  }

  public Aluno criaAluno(AlunoDto params) {
    aluno = new Aluno();
    aplicaParametros(params);
    try {
      return alunoDao.save(aluno);
    } catch (DataIntegrityViolationException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi possível criar o aluno!");
    }
  }

  public Aluno alteraAluno(Long id, AlunoDto params) {
    aluno = alunoDao.findById(id).get();
    validaAlteracao(params);
    aplicaParametros(params);
    try {
      return alunoDao.save(aluno);
    } catch (DataIntegrityViolationException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi possível alterar o aluno!");
    }
  }

  public void deletaAluno(Long id) {
    alunoDao.deleteById(id);
  }

  public List<Aluno> listaAlunos() {
    return (List<Aluno>) alunoDao.findAll();
  }

  private void aplicaParametros(AlunoDto params) {
    aluno.setCpf(Sanitizer.sanitize(Sanitizer.cpf, params.cpf));
    aluno.setRg(Sanitizer.sanitize(Sanitizer.rg, params.rg));
    aluno.setNome(params.nome);
    aluno.setEndereco(params.endereco);
    aluno.setTelefone(Sanitizer.sanitize(Sanitizer.telefone, params.telefone));
    aluno.setEmail(params.email);
    aluno.setHashSenha(Sha256.encryptPassword(params.senha));
    if (params.turmaId != null)
      aluno.setTurma(turmaDao.findById(params.turmaId));
  }

  private void validaAlteracao(AlunoDto params) {
    if (!aluno.getCpf().equals(params.cpf))
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não é possível alterar o cpf do aluno!");
  }
}
