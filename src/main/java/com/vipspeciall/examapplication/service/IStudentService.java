package com.vipspeciall.examapplication.service;

import com.vipspeciall.examapplication.dto.StudentDTO;

import java.util.List;

public interface IStudentService {
    List<StudentDTO> findAllStudents();
    StudentDTO findStudentById(Long id);
    StudentDTO saveStudent(StudentDTO student);
    void deleteStudent(Long id);
}
