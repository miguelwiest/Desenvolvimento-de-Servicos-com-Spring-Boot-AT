package com.example.demo.controller;

import com.example.demo.model.Aluno;
import com.example.demo.model.Matricula;
import com.example.demo.service.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaService matriculaService;

    @PostMapping("/alocar")
    public Matricula alocarAluno(@RequestParam Long alunoId, @RequestParam Long disciplinaId) {
        return matriculaService.alocarAluno(alunoId, disciplinaId);
    }

    @PutMapping("/{matriculaId}/nota")
    public Matricula atribuirNota(@PathVariable Long matriculaId, @RequestParam Double nota) {
        return matriculaService.atribuirNota(matriculaId, nota);
    }

    @GetMapping("/disciplinas/{disciplinaId}/aprovados")
    public List<Aluno> listarAprovados(@PathVariable Long disciplinaId) {
        return matriculaService.listarAprovados(disciplinaId);
    }

    @GetMapping("/disciplinas/{disciplinaId}/reprovados")
    public List<Aluno> listarReprovados(@PathVariable Long disciplinaId) {
        return matriculaService.listarReprovados(disciplinaId);
    }
}
