package com.example.demo.controller;

import com.example.demo.model.Aluno;
import com.example.demo.model.Matricula;
import com.example.demo.service.MatriculaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MatriculaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MatriculaService matriculaService;

    @Test
    public void testAlocarAluno() throws Exception {
        Matricula matricula = new Matricula();
        matricula.setId(1L);

        when(matriculaService.alocarAluno(1L, 1L)).thenReturn(matricula);

        mockMvc.perform(post("/matriculas/alocar")
                .with(httpBasic("professor", "password"))
                .param("alunoId", "1")
                .param("disciplinaId", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    public void testAtribuirNota() throws Exception {
        Matricula matricula = new Matricula();
        matricula.setId(1L);
        matricula.setNota(8.5);

        when(matriculaService.atribuirNota(1L, 8.5)).thenReturn(matricula);

        mockMvc.perform(put("/matriculas/1/nota")
                .with(httpBasic("professor", "password"))
                .param("nota", "8.5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nota").value(8.5));
    }

    @Test
    public void testListarAprovados() throws Exception {
        Aluno aluno1 = new Aluno();
        aluno1.setNome("Aprovado");

        when(matriculaService.listarAprovados(1L)).thenReturn(Arrays.asList(aluno1));

        mockMvc.perform(get("/matriculas/disciplinas/1/aprovados")
                .with(httpBasic("professor", "password")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Aprovado"));
    }

    @Test
    public void testListarReprovados() throws Exception {
        Aluno aluno1 = new Aluno();
        aluno1.setNome("Reprovado");

        when(matriculaService.listarReprovados(1L)).thenReturn(Arrays.asList(aluno1));

        mockMvc.perform(get("/matriculas/disciplinas/1/reprovados")
                .with(httpBasic("professor", "password")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Reprovado"));
    }
}
