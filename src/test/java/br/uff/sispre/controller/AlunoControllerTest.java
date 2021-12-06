package br.uff.sispre.controller;

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
  private AlunoDao repo;

  @Test
  void listaAlunos() throws Exception {
    Aluno aluno1 = repo.save(AlunoFactory.build());
    Aluno aluno2 = repo.save(AlunoFactory.build());

    MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/alunos").contentType("application/json"))
        .andExpect(status().isOk()).andReturn();
    String content = result.getResponse().getContentAsString();

    assertTrue(content.contains(aluno1.getName()));
    assertTrue(content.contains(aluno2.getName()));
  }

  @Test
  void mostraAluno() throws Exception {
    Aluno aluno = repo.save(AlunoFactory.build());

    MvcResult result = mvc
        .perform(MockMvcRequestBuilders.get(String.format("/alunos/%d", aluno.getId())).contentType("application/json"))
        .andExpect(status().isOk()).andReturn();
    String content = result.getResponse().getContentAsString();

    assertTrue(content.contains(aluno.getName()));
    assertTrue(content.contains(aluno.getCpf()));
    assertTrue(content.contains(aluno.getAddress()));
    assertTrue(content.contains(aluno.getRg()));
  }

  @Test
  void criaAlunoValido() throws Exception {
    AlunoDto aluno = new AlunoDto(AlunoFactory.build());
    aluno.password = "Senha_segura";
    aluno.email = "carlinhos@email.com";
    aluno.phoneNumber = "11111111111";

    mvc.perform(MockMvcRequestBuilders.post("/alunos").contentType("application/json")
        .content(objectMapper.writeValueAsString(aluno))).andExpect(status().isOk());

    compare(repo.findByCpf(aluno.cpf), aluno);
  }

  @Test
  void naoCriaAlunoInvalido() throws Exception {
    AlunoDto aluno = new AlunoDto();
    mvc.perform(MockMvcRequestBuilders.post("/alunos").contentType("application/json")
        .content(objectMapper.writeValueAsString(aluno))).andExpect(status().isBadRequest());
  }

  private void compare(Aluno aluno, AlunoDto alunoResource) {
    assertEquals(aluno.getAddress(), alunoResource.address);
    assertEquals(aluno.getRg(), alunoResource.rg);
    assertEquals(aluno.getPhoneNumber(), alunoResource.phoneNumber);
    assertEquals(aluno.getEmail(), alunoResource.email);
    assertEquals(aluno.getName(), alunoResource.name);
    assertEquals(aluno.getPasswordDigest(), Sha256.encryptPassword(alunoResource.password));
  }
}
