package com.example.demo.repository;

import com.example.demo.model.Matricula;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MatriculaRepository extends MongoRepository<Matricula, String> {
    List<Matricula> findByDisciplinaId(String disciplinaId);
}
