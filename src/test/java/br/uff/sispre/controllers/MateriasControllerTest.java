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

import br.uff.sispre.controllers.resources.MateriaResource;
import br.uff.sispre.factories.MateriaFactory;
import br.uff.sispre.models.Materia;
import br.uff.sispre.repositories.MateriaRepository;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class MateriasControllerTest {
  @Autowired
  private MockMvc mvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private MateriaRepository repo;

  @Test
  void listaMaterias() throws Exception {
    Materia materia1 = repo.save(MateriaFactory.build());
    Materia materia2 = repo.save(MateriaFactory.build());

    MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/materias").contentType("application/json"))
        .andExpect(status().isOk()).andReturn();
    String content = result.getResponse().getContentAsString();

    assertTrue(content.contains(materia1.getName()));
    assertTrue(content.contains(materia2.getName()));
  }

  @Test
  void criaMateriaValida() throws Exception {
    MateriaResource materia = new MateriaResource(MateriaFactory.build());

    mvc.perform(MockMvcRequestBuilders.post("/materias").contentType("application/json")
        .content(objectMapper.writeValueAsString(materia))).andExpect(status().isOk());

    compare(repo.findByName(materia.name), materia);
  }

  @Test
  void naoCriaMateriaInvalida() throws Exception {
    MateriaResource materia = new MateriaResource();

    mvc.perform(MockMvcRequestBuilders.post("/materias").contentType("application/json")
        .content(objectMapper.writeValueAsString(materia))).andExpect(status().isBadRequest());
  }

  private void compare(Materia materia, MateriaResource materiaResource) {
    assertEquals(materia.getDescription(), materiaResource.description);
    assertEquals(materia.getName(), materiaResource.name);
  }
}
