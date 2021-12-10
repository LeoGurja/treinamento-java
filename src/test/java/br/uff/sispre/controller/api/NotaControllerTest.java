package br.uff.sispre.controller.api;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import br.uff.sispre.controller.dto.NotaDto;
import br.uff.sispre.dao.NotaDao;
import br.uff.sispre.factory.NotaFactory;
import br.uff.sispre.model.Nota;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class NotaControllerTest {
  @Autowired
  private MockMvc mvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private NotaDao repo;

  @Test
  void listaNotas() throws Exception {
    Nota nota1 = repo.save(NotaFactory.build());
    Nota nota2 = repo.save(NotaFactory.build());

    MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/api/notas").contentType("application/json"))
        .andExpect(status().isOk()).andReturn();
    String content = result.getResponse().getContentAsString();

    assertTrue(content.contains(nota1.getValue().toString()));
    assertTrue(content.contains(nota2.getValue().toString()));
  }

  @Test
  void mostraNota() throws Exception {
    Nota nota = repo.save(NotaFactory.build());

    MvcResult result = mvc
        .perform(
            MockMvcRequestBuilders.get(String.format("/api/notas/%d", nota.getId())).contentType("application/json"))
        .andExpect(status().isOk()).andReturn();
    String content = result.getResponse().getContentAsString();

    assertTrue(content.contains(nota.getValue().toString()));
    assertTrue(content.contains(nota.getMateria().getId().toString()));
    assertTrue(content.contains(nota.getAluno().getId().toString()));
  }

  @Test
  void criaNotaValida() throws Exception {
    NotaDto nota = new NotaDto(repo.save(NotaFactory.build()));

    MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/api/notas").contentType("application/json")
        .content(objectMapper.writeValueAsString(nota))).andExpect(status().isOk()).andReturn();
    String content = result.getResponse().getContentAsString();

    assertTrue(content.contains(nota.alunoId.toString()));
    assertTrue(content.contains(nota.materiaId.toString()));
    assertTrue(content.contains(nota.value.toString()));
  }

  @Test
  void naoCriaNotaInvalida() throws Exception {
    NotaDto nota = new NotaDto();

    mvc.perform(MockMvcRequestBuilders.post("/api/notas").contentType("application/json")
        .content(objectMapper.writeValueAsString(nota))).andExpect(status().isBadRequest());
  }

  @Test
  void deletaNotaExistente() throws Exception {
    Nota nota = repo.save(NotaFactory.build());

    mvc.perform(
        MockMvcRequestBuilders.delete(String.format("/api/notas/%d", nota.getId())).contentType("application/json"))
        .andExpect(status().isOk());
  }
}
