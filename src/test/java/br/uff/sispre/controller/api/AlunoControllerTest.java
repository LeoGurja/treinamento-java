package br.uff.sispre.controller.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.uff.sispre.controller.dto.AlunoDto;
import br.uff.sispre.dao.AlunoDao;
import br.uff.sispre.factory.AlunoFactory;
import br.uff.sispre.helper.Sha256;
import br.uff.sispre.model.Aluno;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class AlunoControllerTest {
  @Autowired
  private MockMvc mvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private AlunoDao alunoDao;

  @Test
  void listaAlunos() throws Exception {
    Aluno aluno1 = alunoDao.save(AlunoFactory.build());
    Aluno aluno2 = alunoDao.save(AlunoFactory.build());

    MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/api/alunos").contentType("application/json"))
        .andExpect(status().isOk()).andReturn();
    String content = result.getResponse().getContentAsString();

    assertTrue(content.contains(aluno1.getNome()));
    assertTrue(content.contains(aluno2.getNome()));
  }

  @Test
  void mostraAluno() throws Exception {
    Aluno aluno = alunoDao.save(AlunoFactory.build());

    MvcResult result = mvc
        .perform(
            MockMvcRequestBuilders.get(String.format("/api/alunos/%d", aluno.getId())).contentType("application/json"))
        .andExpect(status().isOk()).andReturn();
    String content = result.getResponse().getContentAsString();

    assertTrue(content.contains(aluno.getNome()));
    assertTrue(content.contains(aluno.getCpf()));
    assertTrue(content.contains(aluno.getEndereco()));
    assertTrue(content.contains(aluno.getRg()));
  }

  @Test
  void criaAlunoValido() throws Exception {
    AlunoDto aluno = new AlunoDto(AlunoFactory.build());
    aluno.senha = "Senha_segura";
    aluno.email = "carlinhos@email.com";
    aluno.telefone = "11111111111";

    mvc.perform(MockMvcRequestBuilders.post("/api/alunos").contentType("application/json")
        .content(objectMapper.writeValueAsString(aluno))).andExpect(status().isOk());

    compare(alunoDao.findByCpf(aluno.cpf), aluno);
  }

  @Test
  void naoCriaAlunoInvalido() throws Exception {
    AlunoDto aluno = new AlunoDto();
    mvc.perform(MockMvcRequestBuilders.post("/api/alunos").contentType("application/json")
        .content(objectMapper.writeValueAsString(aluno))).andExpect(status().isBadRequest());
  }

  @Test
  void atualizaAluno() throws Exception {
    AlunoDto aluno = new AlunoDto(alunoDao.save(AlunoFactory.build()));
    aluno.senha = "Senha_segura";
    aluno.email = "carlinhos@email.com";
    aluno.telefone = "11111111111";

    mvc.perform(MockMvcRequestBuilders.patch(String.format("/api/alunos/%d", aluno.id)).contentType("application/json")
        .content(objectMapper.writeValueAsString(aluno))).andExpect(status().isOk());

    compare(alunoDao.findByCpf(aluno.cpf), aluno);
  }

  @Test
  void deletaAlunoExistente() throws Exception {
    Aluno aluno = alunoDao.save(AlunoFactory.build());

    mvc.perform(
        MockMvcRequestBuilders.delete(String.format("/api/alunos/%d", aluno.getId())).contentType("application/json"))
        .andExpect(status().isOk());
  }

  private void compare(Aluno aluno, AlunoDto alunoResource) {
    assertEquals(aluno.getEndereco(), alunoResource.endereco);
    assertEquals(aluno.getRg(), alunoResource.rg);
    assertEquals(aluno.getTelefone(), alunoResource.telefone);
    assertEquals(aluno.getEmail(), alunoResource.email);
    assertEquals(aluno.getNome(), alunoResource.nome);
    assertEquals(aluno.getHashSenha(), Sha256.encryptPassword(alunoResource.senha));
  }
}
