package com.example.demo.controller;

import com.example.demo.model.Aluno;
import com.example.demo.service.AlunoService;
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
        aluno.setNome("Teste");
        aluno.setCpf("123.456.789-00");
        aluno.setEmail("teste@test.com");
        aluno.setTelefone("123456789");
        aluno.setEndereco("Rua Teste");

        when(alunoService.criarAluno(any(Aluno.class))).thenReturn(aluno);

        mockMvc.perform(post("/alunos")
                .with(httpBasic("professor", "password"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(aluno)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Teste"));
    }

    @Test
    public void testListarAlunos() throws Exception {
        Aluno aluno1 = new Aluno();
        aluno1.setNome("Teste 1");

        Aluno aluno2 = new Aluno();
        aluno2.setNome("Teste 2");

        when(alunoService.listarAlunos()).thenReturn(Arrays.asList(aluno1, aluno2));

        mockMvc.perform(get("/alunos")
                .with(httpBasic("professor", "password")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Teste 1"))
                .andExpect(jsonPath("$[1].nome").value("Teste 2"));
    }
}
