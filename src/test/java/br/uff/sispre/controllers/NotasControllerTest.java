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

import br.uff.sispre.controllers.resources.NotaResource;
import br.uff.sispre.models.Aluno;
import br.uff.sispre.models.Materia;
import br.uff.sispre.models.Nota;
import br.uff.sispre.repositories.AlunoRepository;
import br.uff.sispre.repositories.MateriaRepository;
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

  @Autowired
  private AlunoRepository alunoRepo;

  @Autowired
  private MateriaRepository materiaRepo;

  @Test
  void criaNotaValida() throws Exception {
    NotaResource nota = new NotaResource();
    Aluno aluno = criaAluno();
    Materia materia = criaMateria();

    nota.value = 10.0;
    nota.alunoId = aluno.getId();
    nota.materiaId = materia.getId();

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

  private Aluno criaAluno() {
    Aluno aluno = new Aluno();
    aluno.setCpf("11111111111");
    aluno.setRg("111111111");
    aluno.setName("Carlinhos");
    aluno.setAddress("Algum lugar");
    return alunoRepo.save(aluno);
  }

  private Materia criaMateria() {
    Materia materia = new Materia();
    materia.setName("Culinária I");
    materia.setDescription("Aprender a utilizar o microondas");
    return materiaRepo.save(materia);
  }
}
