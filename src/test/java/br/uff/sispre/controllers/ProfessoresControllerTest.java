package br.uff.sispre.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.uff.sispre.controllers.resources.ProfessorResource;
import br.uff.sispre.factories.ProfessorFactory;
import br.uff.sispre.helpers.Sha256;
import br.uff.sispre.models.Professor;
import br.uff.sispre.repositories.ProfessorRepository;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class ProfessoresControllerTest {
  @Autowired
  private MockMvc mvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private ProfessorRepository repo;

  @Test
  void criaProfessorValido() throws Exception {
    ProfessorResource professor = new ProfessorResource(ProfessorFactory.build());
    professor.password = "Senha_segura";
    professor.email = "carlinhos@email.com";
    professor.phoneNumber = "11111111111";

    mvc.perform(MockMvcRequestBuilders.post("/professores").contentType("application/json")
        .content(objectMapper.writeValueAsString(professor))).andExpect(status().isOk());

    compare(repo.findByCpf(professor.cpf), professor);
  }

  @Test
  void naoCriaProfessorInvalido() throws Exception {
    ProfessorResource professor = new ProfessorResource();
    mvc.perform(MockMvcRequestBuilders.post("/professores").contentType("application/json")
        .content(objectMapper.writeValueAsString(professor))).andExpect(status().isBadRequest());
  }

  private void compare(Professor professor, ProfessorResource professorResource) {
    assertEquals(professor.getAddress(), professorResource.address);
    assertEquals(professor.getCpf(), professorResource.cpf);
    assertEquals(professor.getRg(), professorResource.rg);
    assertEquals(professor.getPhoneNumber(), professorResource.phoneNumber);
    assertEquals(professor.getEmail(), professorResource.email);
    assertEquals(professor.getName(), professorResource.name);
    assertEquals(professor.getPasswordDigest(), Sha256.encryptPassword(professorResource.password));
  }
}
