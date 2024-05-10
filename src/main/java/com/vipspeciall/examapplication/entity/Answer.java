package com.vipspeciall.examapplication.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Answer {
    private Long id;
    private String answerText;
    private boolean correct;
    @ManyToOne
    private Question question;
}
