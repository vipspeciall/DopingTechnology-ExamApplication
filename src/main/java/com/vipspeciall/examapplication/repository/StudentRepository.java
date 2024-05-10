package com.vipspeciall.examapplication.repository;

import com.vipspeciall.examapplication.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
