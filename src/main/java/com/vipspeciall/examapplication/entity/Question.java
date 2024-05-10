package com.vipspeciall.examapplication.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String questionText;
    @OneToMany
    private List<Answer> answers;
    @ManyToOne
    private Exam exam;

}
