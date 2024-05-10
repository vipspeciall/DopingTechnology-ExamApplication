package com.vipspeciall.examapplication.repository;

import com.vipspeciall.examapplication.entity.ExamSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamSessionRepository extends JpaRepository<ExamSession, Long> {
}
