package com.example.demo.service;

import com.example.demo.model.Aluno;
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

    public Matricula alocarAluno(String alunoId, String disciplinaId) {
        alunoRepository.findById(alunoId).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
        disciplinaRepository.findById(disciplinaId).orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));

        Matricula matricula = new Matricula();
        matricula.setAlunoId(alunoId);
        matricula.setDisciplinaId(disciplinaId);

        return matriculaRepository.save(matricula);
    }

    public Matricula atribuirNota(String matriculaId, Double nota) {
        Matricula matricula = matriculaRepository.findById(matriculaId).orElseThrow(() -> new RuntimeException("Matrícula não encontrada"));
        matricula.setNota(nota);
        return matriculaRepository.save(matricula);
    }

    public List<Aluno> listarAprovados(String disciplinaId) {
        List<Matricula> matriculas = matriculaRepository.findByDisciplinaId(disciplinaId);
        List<String> alunoIds = matriculas.stream()
                .filter(matricula -> matricula.getNota() != null && matricula.getNota() >= 7)
                .map(Matricula::getAlunoId)
                .collect(Collectors.toList());
        return (List<Aluno>) alunoRepository.findAllById(alunoIds);
    }

    public List<Aluno> listarReprovados(String disciplinaId) {
        List<Matricula> matriculas = matriculaRepository.findByDisciplinaId(disciplinaId);
        List<String> alunoIds = matriculas.stream()
                .filter(matricula -> matricula.getNota() != null && matricula.getNota() < 7)
                .map(Matricula::getAlunoId)
                .collect(Collectors.toList());
        return (List<Aluno>) alunoRepository.findAllById(alunoIds);
    }
}
