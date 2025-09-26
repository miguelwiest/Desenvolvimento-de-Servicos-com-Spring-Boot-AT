package com.example.demo.repository;

import com.example.demo.model.Professor;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProfessorRepository extends MongoRepository<Professor, String> {
    Optional<Professor> findByUsername(String username);
}
