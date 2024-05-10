package com.vipspeciall.examapplication.repository;

import com.vipspeciall.examapplication.entity.ExamResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamResultRepository extends JpaRepository<ExamResult, Long> {
}
