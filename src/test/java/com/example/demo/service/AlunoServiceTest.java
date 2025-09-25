package com.example.demo.service;

import com.example.demo.model.Aluno;
import com.example.demo.repository.AlunoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AlunoServiceTest {

    @InjectMocks
    private AlunoService alunoService;

    @Mock
    private AlunoRepository alunoRepository;

    @Test
    public void testCriarAluno() {
        Aluno aluno = new Aluno();
        aluno.setNome("Teste");

        when(alunoRepository.save(aluno)).thenReturn(aluno);

        Aluno result = alunoService.criarAluno(aluno);
        assertEquals("Teste", result.getNome());
    }

    @Test
    public void testListarAlunos() {
        Aluno aluno1 = new Aluno();
        aluno1.setNome("Teste 1");

        Aluno aluno2 = new Aluno();
        aluno2.setNome("Teste 2");

        List<Aluno> alunos = Arrays.asList(aluno1, aluno2);

        when(alunoRepository.findAll()).thenReturn(alunos);

        List<Aluno> result = alunoService.listarAlunos();
        assertEquals(2, result.size());
        assertEquals("Teste 1", result.get(0).getNome());
        assertEquals("Teste 2", result.get(1).getNome());
    }
}
