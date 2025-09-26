package com.example.demo.controller;

import com.example.demo.model.Disciplina;
import com.example.demo.service.DisciplinaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.config.SecurityConfig;
import org.springframework.context.annotation.Import;

@WebMvcTest(DisciplinaController.class)
@Import(SecurityConfig.class)
@WithMockUser(username = "professor")
public class DisciplinaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DisciplinaService disciplinaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCriarDisciplina() throws Exception {
        Disciplina disciplina = new Disciplina();
        disciplina.setNome("Nova Disciplina");
        disciplina.setCodigo("COD123");

        when(disciplinaService.criarDisciplina(any(Disciplina.class))).thenReturn(disciplina);

        mockMvc.perform(post("/disciplinas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(disciplina)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Nova Disciplina"));
    }

    @Test
    public void testListarDisciplinas() throws Exception {
        Disciplina disciplina1 = new Disciplina();
        disciplina1.setNome("Disciplina 1");
        Disciplina disciplina2 = new Disciplina();
        disciplina2.setNome("Disciplina 2");

        when(disciplinaService.listarDisciplinas()).thenReturn(Arrays.asList(disciplina1, disciplina2));

        mockMvc.perform(get("/disciplinas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Disciplina 1"))
                .andExpect(jsonPath("$[1].nome").value("Disciplina 2"));
    }
}
