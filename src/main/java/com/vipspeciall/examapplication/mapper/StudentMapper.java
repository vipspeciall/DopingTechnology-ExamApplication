package com.vipspeciall.examapplication.mapper;

import com.vipspeciall.examapplication.dto.StudentDTO;
import com.vipspeciall.examapplication.entity.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface StudentMapper {

    Student studentDTOToStudent(StudentDTO studentDTO);
    StudentDTO studentToStudentDTO(Student student);
}
