package com.example.demo.service;

import com.example.demo.model.Disciplina;
import com.example.demo.repository.DisciplinaRepository;
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
public class DisciplinaServiceTest {

    @InjectMocks
    private DisciplinaService disciplinaService;

    @Mock
    private DisciplinaRepository disciplinaRepository;

    @Test
    public void testCriarDisciplina() {
        Disciplina disciplina = new Disciplina();
        disciplina.setNome("Teste");

        when(disciplinaRepository.save(disciplina)).thenReturn(disciplina);

        Disciplina result = disciplinaService.criarDisciplina(disciplina);
        assertEquals("Teste", result.getNome());
    }

    @Test
    public void testListarDisciplinas() {
        Disciplina disciplina1 = new Disciplina();
        disciplina1.setNome("Teste 1");

        Disciplina disciplina2 = new Disciplina();
        disciplina2.setNome("Teste 2");

        List<Disciplina> disciplinas = Arrays.asList(disciplina1, disciplina2);

        when(disciplinaRepository.findAll()).thenReturn(disciplinas);

        List<Disciplina> result = disciplinaService.listarDisciplinas();
        assertEquals(2, result.size());
        assertEquals("Teste 1", result.get(0).getNome());
        assertEquals("Teste 2", result.get(1).getNome());
    }
}
