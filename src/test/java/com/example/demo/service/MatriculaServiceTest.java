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
    public void testAlocarAluno() {
        Aluno aluno = new Aluno();
        aluno.setId(1L);

        Disciplina disciplina = new Disciplina();
        disciplina.setId(1L);

        when(alunoRepository.findById(1L)).thenReturn(Optional.of(aluno));
        when(disciplinaRepository.findById(1L)).thenReturn(Optional.of(disciplina));
        when(matriculaRepository.save(any(Matricula.class))).thenAnswer(i -> i.getArguments()[0]);

        Matricula result = matriculaService.alocarAluno(1L, 1L);
        assertEquals(1L, result.getAluno().getId());
        assertEquals(1L, result.getDisciplina().getId());
    }

    @Test
    public void testAtribuirNota() {
        Matricula matricula = new Matricula();
        matricula.setId(1L);

        when(matriculaRepository.findById(1L)).thenReturn(Optional.of(matricula));
        when(matriculaRepository.save(any(Matricula.class))).thenAnswer(i -> i.getArguments()[0]);

        Matricula result = matriculaService.atribuirNota(1L, 8.5);
        assertEquals(8.5, result.getNota());
    }

    @Test
    public void testListarAprovados() {
        Aluno aluno1 = new Aluno();
        aluno1.setId(1L);

        Matricula matricula1 = new Matricula();
        matricula1.setAluno(aluno1);
        matricula1.setNota(8.0);

        Aluno aluno2 = new Aluno();
        aluno2.setId(2L);

        Matricula matricula2 = new Matricula();
        matricula2.setAluno(aluno2);
        matricula2.setNota(6.0);

        when(matriculaRepository.findByDisciplinaId(1L)).thenReturn(Arrays.asList(matricula1, matricula2));

        List<Aluno> result = matriculaService.listarAprovados(1L);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
    }

    @Test
    public void testListarReprovados() {
        Aluno aluno1 = new Aluno();
        aluno1.setId(1L);

        Matricula matricula1 = new Matricula();
        matricula1.setAluno(aluno1);
        matricula1.setNota(8.0);

        Aluno aluno2 = new Aluno();
        aluno2.setId(2L);

        Matricula matricula2 = new Matricula();
        matricula2.setAluno(aluno2);
        matricula2.setNota(6.0);

        when(matriculaRepository.findByDisciplinaId(1L)).thenReturn(Arrays.asList(matricula1, matricula2));

        List<Aluno> result = matriculaService.listarReprovados(1L);
        assertEquals(1, result.size());
        assertEquals(2L, result.get(0).getId());
    }
}
