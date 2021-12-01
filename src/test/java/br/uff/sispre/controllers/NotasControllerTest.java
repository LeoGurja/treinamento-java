package br.uff.sispre.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import br.uff.sispre.controllers.resources.NotaResource;
import br.uff.sispre.factories.NotaFactory;
import br.uff.sispre.models.Nota;
import br.uff.sispre.repositories.NotaRepository;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class NotasControllerTest {
  @Autowired
  private MockMvc mvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private NotaRepository repo;

  @Test
  void listaNotas() throws Exception {
    Nota nota1 = repo.save(NotaFactory.build());
    Nota nota2 = repo.save(NotaFactory.build());

    MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/notas").contentType("application/json"))
        .andExpect(status().isOk()).andReturn();
    String content = result.getResponse().getContentAsString();

    assertTrue(content.contains(nota1.getValue().toString()));
    assertTrue(content.contains(nota2.getValue().toString()));
  }

  @Test
  void criaNotaValida() throws Exception {
    NotaResource nota = new NotaResource(repo.save(NotaFactory.build()));

    mvc.perform(MockMvcRequestBuilders.post("/notas").contentType("application/json")
        .content(objectMapper.writeValueAsString(nota))).andExpect(status().isOk());

    compare(repo.findAll().iterator().next(), nota);
  }

  @Test
  void naoCriaNotaInvalida() throws Exception {
    NotaResource nota = new NotaResource();

    mvc.perform(MockMvcRequestBuilders.post("/notas").contentType("application/json")
        .content(objectMapper.writeValueAsString(nota))).andExpect(status().isBadRequest());
  }

  private void compare(Nota nota, NotaResource notaResource) {
    assertEquals(nota.getAluno().getId(), notaResource.alunoId);
    assertEquals(nota.getMateria().getId(), notaResource.materiaId);
    assertEquals(nota.getValue(), notaResource.value);
  }
}
