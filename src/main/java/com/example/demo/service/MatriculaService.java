package com.example.demo.service;

import com.example.demo.model.Aluno;
import com.example.demo.model.Disciplina;
import com.example.demo.model.Matricula;
import com.example.demo.repository.AlunoRepository;
import com.example.demo.repository.DisciplinaRepository;
import com.example.demo.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatriculaService {

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    public Matricula alocarAluno(Long alunoId, Long disciplinaId) {
        Aluno aluno = alunoRepository.findById(alunoId).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
        Disciplina disciplina = disciplinaRepository.findById(disciplinaId).orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));

        Matricula matricula = new Matricula();
        matricula.setAluno(aluno);
        matricula.setDisciplina(disciplina);

        return matriculaRepository.save(matricula);
    }

    public Matricula atribuirNota(Long matriculaId, Double nota) {
        Matricula matricula = matriculaRepository.findById(matriculaId).orElseThrow(() -> new RuntimeException("Matrícula não encontrada"));
        matricula.setNota(nota);
        return matriculaRepository.save(matricula);
    }

    public List<Aluno> listarAprovados(Long disciplinaId) {
        List<Matricula> matriculas = matriculaRepository.findByDisciplinaId(disciplinaId);
        return matriculas.stream()
                .filter(matricula -> matricula.getNota() != null && matricula.getNota() >= 7)
                .map(Matricula::getAluno)
                .collect(Collectors.toList());
    }

    public List<Aluno> listarReprovados(Long disciplinaId) {
        List<Matricula> matriculas = matriculaRepository.findByDisciplinaId(disciplinaId);
        return matriculas.stream()
                .filter(matricula -> matricula.getNota() != null && matricula.getNota() < 7)
                .map(Matricula::getAluno)
                .collect(Collectors.toList());
    }
}
