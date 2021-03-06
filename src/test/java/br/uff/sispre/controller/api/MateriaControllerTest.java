package br.uff.sispre.controller.api;

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

import br.uff.sispre.controller.dto.MateriaDto;
import br.uff.sispre.dao.MateriaDao;
import br.uff.sispre.factory.MateriaFactory;
import br.uff.sispre.model.Materia;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class MateriaControllerTest {
  @Autowired
  private MockMvc mvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private MateriaDao repo;

  @Test
  void listaMaterias() throws Exception {
    Materia materia1 = repo.save(MateriaFactory.build());
    Materia materia2 = repo.save(MateriaFactory.build());

    MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/api/materias").contentType("application/json"))
        .andExpect(status().isOk()).andReturn();
    String content = result.getResponse().getContentAsString();

    assertTrue(content.contains(materia1.getName()));
    assertTrue(content.contains(materia2.getName()));
  }

  @Test
  void mostraMateria() throws Exception {
    Materia materia = repo.save(MateriaFactory.build());

    MvcResult result = mvc
        .perform(
            MockMvcRequestBuilders.get(String.format("/api/materias/%d", materia.getId()))
                .contentType("application/json"))
        .andExpect(status().isOk()).andReturn();
    String content = result.getResponse().getContentAsString();

    assertTrue(content.contains(materia.getName()));
    assertTrue(content.contains(materia.getDescription()));
  }

  @Test
  void criaMateriaValida() throws Exception {
    MateriaDto materia = new MateriaDto(MateriaFactory.build());

    mvc.perform(MockMvcRequestBuilders.post("/api/materias").contentType("application/json")
        .content(objectMapper.writeValueAsString(materia))).andExpect(status().isOk());

    compare(repo.findByName(materia.name), materia);
  }

  @Test
  void naoCriaMateriaInvalida() throws Exception {
    MateriaDto materia = new MateriaDto();

    mvc.perform(MockMvcRequestBuilders.post("/api/materias").contentType("application/json")
        .content(objectMapper.writeValueAsString(materia))).andExpect(status().isBadRequest());
  }

  @Test
  void deletaMateriaExistente() throws Exception {
    Materia materia = repo.save(MateriaFactory.build());

    mvc.perform(
        MockMvcRequestBuilders.delete(String.format("/api/materias/%d", materia.getId()))
            .contentType("application/json"))
        .andExpect(status().isOk());
  }

  private void compare(Materia materia, MateriaDto materiaResource) {
    assertEquals(materia.getDescription(), materiaResource.description);
    assertEquals(materia.getName(), materiaResource.name);
  }
}
