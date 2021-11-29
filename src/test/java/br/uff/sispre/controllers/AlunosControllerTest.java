package br.uff.sispre.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.uff.sispre.controllers.resources.AlunoResource;
import br.uff.sispre.repositories.AlunoRepository;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class AlunosControllerTest {
  @Autowired
  private MockMvc mvc;

  @Autowired
  private ObjectMapper objectMapper;

  // @Autowired
  // private AlunoRepository repo;

  @Test
  void criaAlunoValido() throws Exception {
    AlunoResource aluno = new AlunoResource();
    aluno.cpf = "111.111.111-11";
    aluno.rg = "11.111.111-1";
    aluno.name = "Carlinhos de Jesus";
    aluno.address = "Rua dos Ladrões, 0";
    aluno.password = "Senha_segura";
    aluno.email = "carlinhos@email.com";
    aluno.phoneNumber = "(11)11111-1111";
    mvc.perform(MockMvcRequestBuilders.post("/alunos").contentType("application/json")
        .content(objectMapper.writeValueAsString(aluno))).andExpect(status().isOk());
  }

  @Test
  void naoCriaAlunoInvalido() throws Exception {
    AlunoResource aluno = new AlunoResource();
    mvc.perform(MockMvcRequestBuilders.post("/alunos").contentType("application/json")
        .content(objectMapper.writeValueAsString(aluno))).andExpect(status().isBadRequest());
  }
}
