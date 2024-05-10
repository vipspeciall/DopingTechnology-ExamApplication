package com.vipspeciall.examapplication.repository;

import com.vipspeciall.examapplication.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<Exam, Long> {
}
