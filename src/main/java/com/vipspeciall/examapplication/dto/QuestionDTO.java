package com.vipspeciall.examapplication.dto;

import lombok.Data;

import java.util.List;

@Data
public class QuestionDTO {
    private Long id;
    private String questionText;
    private List<AnswerDTO> answers;
}
