package com.example.demo.controller;

import com.example.demo.model.Aluno;
import com.example.demo.service.AlunoService;
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

@WebMvcTest(AlunoController.class)
@Import(SecurityConfig.class)
@WithMockUser(username = "professor")
public class AlunoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlunoService alunoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCriarAluno() throws Exception {
        Aluno aluno = new Aluno();
        aluno.setNome("Novo Aluno");
        aluno.setCpf("123.456.789-00");
        aluno.setEmail("aluno@test.com");
        aluno.setTelefone("123456789");
        aluno.setEndereco("Rua Teste");

        when(alunoService.criarAluno(any(Aluno.class))).thenReturn(aluno);

        mockMvc.perform(post("/alunos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(aluno)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Novo Aluno"));
    }

    @Test
    public void testListarAlunos() throws Exception {
        Aluno aluno1 = new Aluno();
        aluno1.setNome("Aluno 1");
        Aluno aluno2 = new Aluno();
        aluno2.setNome("Aluno 2");

        when(alunoService.listarAlunos()).thenReturn(Arrays.asList(aluno1, aluno2));

        mockMvc.perform(get("/alunos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Aluno 1"))
                .andExpect(jsonPath("$[1].nome").value("Aluno 2"));
    }
}
