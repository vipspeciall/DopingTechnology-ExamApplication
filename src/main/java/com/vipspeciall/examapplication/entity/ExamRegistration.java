package com.vipspeciall.examapplication.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class ExamRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "exam_id", nullable = false)
    private Exam exam;

    private Double score; // Test sonucu olarak öğrencinin aldığı puan

    private Boolean completed; // Testin tamamlanıp tamamlanmadığı

    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationDate; // Kayıt tarihi
}
