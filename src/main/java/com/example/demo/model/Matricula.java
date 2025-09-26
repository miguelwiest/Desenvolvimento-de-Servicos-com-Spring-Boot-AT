package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection = "matriculas")
public class Matricula {

    @Id
    private String id;

    private String alunoId;

    private String disciplinaId;

    private Double nota;
}
