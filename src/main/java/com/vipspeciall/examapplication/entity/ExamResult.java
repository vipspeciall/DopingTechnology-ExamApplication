package com.vipspeciall.examapplication.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExamResult {
    private Long id;

    private Long studentId;
    private Long examId;
    private int score;
    private LocalDateTime endTime;
    private int timeSpent;

    public ExamResult(Long studentId, Long examId, int score) {
        this.studentId = studentId;
        this.examId = examId;
        this.score = score;
    }
}
