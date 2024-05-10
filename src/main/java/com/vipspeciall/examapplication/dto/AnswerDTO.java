package com.vipspeciall.examapplication.dto;

import lombok.Data;

@Data
public class AnswerDTO {
    private Long id;
    private String answerText;
    private boolean correct;
}
