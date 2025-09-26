package com.example.demo.service;

import com.example.demo.model.Aluno;
import com.example.demo.model.Disciplina;
import com.example.demo.model.Matricula;
import com.example.demo.repository.AlunoRepository;
import com.example.demo.repository.DisciplinaRepository;
import com.example.demo.repository.MatriculaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MatriculaServiceTest {

    @InjectMocks
    private MatriculaService matriculaService;

    @Mock
    private MatriculaRepository matriculaRepository;

    @Mock
    private AlunoRepository alunoRepository;

    @Mock
    private DisciplinaRepository disciplinaRepository;

    @Test
    void testAlocarAluno() {
        Aluno aluno = new Aluno();
        aluno.setId("1");
        Disciplina disciplina = new Disciplina();
        disciplina.setId("1");

        when(alunoRepository.findById("1")).thenReturn(Optional.of(aluno));
        when(disciplinaRepository.findById("1")).thenReturn(Optional.of(disciplina));
        when(matriculaRepository.save(any(Matricula.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Matricula result = matriculaService.alocarAluno("1", "1");

        assertEquals("1", result.getAlunoId());
        assertEquals("1", result.getDisciplinaId());
    }

    @Test
    void testAtribuirNota() {
        Matricula matricula = new Matricula();
        matricula.setId("1");

        when(matriculaRepository.findById("1")).thenReturn(Optional.of(matricula));
        when(matriculaRepository.save(any(Matricula.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Matricula result = matriculaService.atribuirNota("1", 8.5);

        assertEquals(8.5, result.getNota());
    }

    @Test
    void testListarAprovados() {
        Aluno aluno1 = new Aluno();
        aluno1.setId("1");

        Matricula matricula1 = new Matricula();
        matricula1.setAlunoId("1");
        matricula1.setNota(8.0);

        when(matriculaRepository.findByDisciplinaId("1")).thenReturn(Arrays.asList(matricula1));
        when(alunoRepository.findAllById(Arrays.asList("1"))).thenReturn(Arrays.asList(aluno1));

        List<Aluno> result = matriculaService.listarAprovados("1");

        assertEquals(1, result.size());
        assertEquals("1", result.get(0).getId());
    }

    @Test
    void testListarReprovados() {
        Aluno aluno1 = new Aluno();
        aluno1.setId("1");

        Matricula matricula1 = new Matricula();
        matricula1.setAlunoId("1");
        matricula1.setNota(6.0);

        when(matriculaRepository.findByDisciplinaId("1")).thenReturn(Arrays.asList(matricula1));
        when(alunoRepository.findAllById(Arrays.asList("1"))).thenReturn(Arrays.asList(aluno1));

        List<Aluno> result = matriculaService.listarReprovados("1");

        assertEquals(1, result.size());
        assertEquals("1", result.get(0).getId());
    }
}
