package com.example.demo.repository;

import com.example.demo.model.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
    List<Matricula> findByDisciplinaId(Long disciplinaId);
}
