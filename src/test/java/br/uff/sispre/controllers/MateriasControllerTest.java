package br.uff.sispre.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import br.uff.sispre.controllers.resources.MateriaResource;
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
  void criaMateriaValida() throws Exception {
    MateriaResource materia = new MateriaResource();

    materia.name = "Matemática";
    materia.description = "Calcular várias coisas diferentes";

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
