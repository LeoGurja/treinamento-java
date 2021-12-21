package br.uff.sispre.controller.api;

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

import br.uff.sispre.controller.dto.TurmaDto;
import br.uff.sispre.dao.TurmaDao;
import br.uff.sispre.factory.TurmaFactory;
import br.uff.sispre.model.Turma;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class TurmaControllerTest {
  @Autowired
  private MockMvc mvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private TurmaDao turmaDao;

  @Test
  void listaTurma() throws Exception {
    Turma turma1 = turmaDao.save(TurmaFactory.build());
    Turma turma2 = turmaDao.save(TurmaFactory.build());

    MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/api/turmas").contentType("application/json"))
        .andExpect(status().isOk()).andReturn();
    String content = result.getResponse().getContentAsString();

    assertTrue(content.contains(turma1.getNome()));
    assertTrue(content.contains(turma2.getNome()));
  }

  @Test
  void mostraTurma() throws Exception {
    Turma turma = turmaDao.save(TurmaFactory.build());

    MvcResult result = mvc
        .perform(
            MockMvcRequestBuilders.get(String.format("/api/turmas/%d", turma.getId())).contentType("application/json"))
        .andExpect(status().isOk()).andReturn();
    String content = result.getResponse().getContentAsString();

    assertTrue(content.contains(turma.getNome()));
  }

  @Test
  void criaTurmaValida() throws Exception {
    TurmaDto turma = new TurmaDto(TurmaFactory.build());

    mvc.perform(MockMvcRequestBuilders.post("/api/turmas").contentType("application/json")
        .content(objectMapper.writeValueAsString(turma))).andExpect(status().isOk());

    compare(turmaDao.findByNome(turma.nome), turma);
  }

  @Test
  void naoCriaTurmaInvalida() throws Exception {
    TurmaDto turma = new TurmaDto();

    mvc.perform(MockMvcRequestBuilders.post("/api/turmas").contentType("application/json")
        .content(objectMapper.writeValueAsString(turma))).andExpect(status().isBadRequest());
  }

  @Test
  void deletaTurmaExistente() throws Exception {
    Turma turma = turmaDao.save(TurmaFactory.build());

    mvc.perform(
        MockMvcRequestBuilders.delete(String.format("/api/turmas/%d", turma.getId())).contentType("application/json"))
        .andExpect(status().isOk());
  }

  private void compare(Turma turma, TurmaDto turmaResource) {
    assertEquals(turma.getNome(), turmaResource.nome);
  }
}
