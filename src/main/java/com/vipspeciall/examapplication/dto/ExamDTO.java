package com.vipspeciall.examapplication.dto;

import lombok.Data;

import java.util.List;

@Data
public class ExamDTO {
    private Long id;
    private String title;
    private List<QuestionDTO> questions;
}
