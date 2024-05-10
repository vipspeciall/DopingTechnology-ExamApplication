package com.vipspeciall.examapplication.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class ExamSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentId;
    private Long examId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer totalAllowedContinuations = 5;  // Toplam izin verilen devam ettirme hakkı
    private Integer continuationsUsed = 0;  // Kullanılan devam ettirme hakkı
    private Integer timeSpent = 0; // Dakika cinsinden geçirilen süre
    private Boolean isCompleted = false; // Sınavın tamamlanıp tamamlanmadığı
    private String currentAnswers; // Cevapların JSON olarak kaydedilmesi

}