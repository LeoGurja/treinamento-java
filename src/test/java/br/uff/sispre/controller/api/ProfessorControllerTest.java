package br.uff.sispre.controller.api;

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

import br.uff.sispre.controller.dto.ProfessorDto;
import br.uff.sispre.dao.ProfessorDao;
import br.uff.sispre.factory.ProfessorFactory;
import br.uff.sispre.helper.Sha256;
import br.uff.sispre.model.Professor;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class ProfessorControllerTest {
  @Autowired
  private MockMvc mvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private ProfessorDao professorDao;

  @Test
  void listaProfessores() throws Exception {
    Professor professor1 = professorDao.save(ProfessorFactory.build());
    Professor professor2 = professorDao.save(ProfessorFactory.build());

    MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/api/professores").contentType("application/json"))
        .andExpect(status().isOk()).andReturn();
    String content = result.getResponse().getContentAsString();

    assertTrue(content.contains(professor1.getNome()));
    assertTrue(content.contains(professor2.getNome()));
  }

  @Test
  void mostraProfessor() throws Exception {
    Professor professor = professorDao.save(ProfessorFactory.build());

    MvcResult result = mvc
        .perform(MockMvcRequestBuilders.get(String.format("/api/professores/%d", professor.getId()))
            .contentType("application/json"))
        .andExpect(status().isOk()).andReturn();
    String content = result.getResponse().getContentAsString();

    assertTrue(content.contains(professor.getNome()));
    assertTrue(content.contains(professor.getCpf()));
    assertTrue(content.contains(professor.getEndereco()));
    assertTrue(content.contains(professor.getRg()));
  }

  @Test
  void criaProfessorValido() throws Exception {
    ProfessorDto professor = new ProfessorDto(ProfessorFactory.build());
    professor.senha= "Senha_segura";
    professor.email = "carlinhos@email.com";
    professor.telefone = "11111111111";

    mvc.perform(MockMvcRequestBuilders.post("/api/professores").contentType("application/json")
        .content(objectMapper.writeValueAsString(professor))).andExpect(status().isOk());

    compare(professorDao.findByCpf(professor.cpf), professor);
  }

  @Test
  void naoCriaProfessorInvalido() throws Exception {
    ProfessorDto professor = new ProfessorDto();
    mvc.perform(MockMvcRequestBuilders.post("/api/professores").contentType("application/json")
        .content(objectMapper.writeValueAsString(professor))).andExpect(status().isBadRequest());
  }

  @Test
  void deletaProfessorExistente() throws Exception {
    Professor professor = professorDao.save(ProfessorFactory.build());

    mvc.perform(
        MockMvcRequestBuilders.delete(String.format("/api/professores/%d", professor.getId()))
            .contentType("application/json"))
        .andExpect(status().isOk());
  }

  private void compare(Professor professor, ProfessorDto professorResource) {
    assertEquals(professor.getEndereco(), professorResource.endereco);
    assertEquals(professor.getCpf(), professorResource.cpf);
    assertEquals(professor.getRg(), professorResource.rg);
    assertEquals(professor.getTelefone(), professorResource.telefone);
    assertEquals(professor.getEmail(), professorResource.email);
    assertEquals(professor.getNome(), professorResource.nome);
    assertEquals(professor.getHashSenha(), Sha256.encryptPassword(professorResource.senha));
  }
}
