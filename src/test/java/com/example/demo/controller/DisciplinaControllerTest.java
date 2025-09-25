package com.example.demo.controller;

import com.example.demo.model.Disciplina;
import com.example.demo.service.DisciplinaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
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
        disciplina.setNome("Teste");
        disciplina.setCodigo("CS101");

        when(disciplinaService.criarDisciplina(any(Disciplina.class))).thenReturn(disciplina);

        mockMvc.perform(post("/disciplinas")
                .with(httpBasic("professor", "password"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(disciplina)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Teste"));
    }

    @Test
    public void testListarDisciplinas() throws Exception {
        Disciplina disciplina1 = new Disciplina();
        disciplina1.setNome("Teste 1");

        Disciplina disciplina2 = new Disciplina();
        disciplina2.setNome("Teste 2");

        when(disciplinaService.listarDisciplinas()).thenReturn(Arrays.asList(disciplina1, disciplina2));

        mockMvc.perform(get("/disciplinas")
                .with(httpBasic("professor", "password")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Teste 1"))
                .andExpect(jsonPath("$[1].nome").value("Teste 2"));
    }
}
