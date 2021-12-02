package br.uff.sispre.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;

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

import br.uff.sispre.controllers.resources.TurmaResource;
import br.uff.sispre.factories.TurmaFactory;
import br.uff.sispre.models.Turma;
import br.uff.sispre.repositories.TurmaRepository;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class TurmasControllerTest {
  @Autowired
  private MockMvc mvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private TurmaRepository repo;

  @Test
  void listaTurma() throws Exception {
    Turma turma1 = repo.save(TurmaFactory.build());
    Turma turma2 = repo.save(TurmaFactory.build());

    MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/turmas").contentType("application/json"))
        .andExpect(status().isOk()).andReturn();
    String content = result.getResponse().getContentAsString();

    assertTrue(content.contains(turma1.getName()));
    assertTrue(content.contains(turma2.getName()));
  }

  @Test
  void mostraTurma() throws Exception {
    Turma turma = repo.save(TurmaFactory.build());

    MvcResult result = mvc
        .perform(MockMvcRequestBuilders.get(String.format("/turmas/%d", turma.getId())).contentType("application/json"))
        .andExpect(status().isOk()).andReturn();
    String content = result.getResponse().getContentAsString();

    assertTrue(content.contains(turma.getName()));
  }

  @Test
  void criaTurmaValida() throws Exception {
    TurmaResource turma = new TurmaResource(TurmaFactory.build());

    mvc.perform(MockMvcRequestBuilders.post("/turmas").contentType("application/json")
        .content(objectMapper.writeValueAsString(turma))).andExpect(status().isOk());

    compare(repo.findByName(turma.name), turma);
  }

  @Test
  void naoCriaTurmaInvalida() throws Exception {
    TurmaResource turma = new TurmaResource();

    mvc.perform(MockMvcRequestBuilders.post("/turmas").contentType("application/json")
        .content(objectMapper.writeValueAsString(turma))).andExpect(status().isBadRequest());
  }

  private void compare(Turma turma, TurmaResource turmaResource) {
    assertEquals(turma.getName(), turmaResource.name);
  }
}
