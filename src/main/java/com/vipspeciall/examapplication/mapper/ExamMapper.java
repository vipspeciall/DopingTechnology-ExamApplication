package com.vipspeciall.examapplication.mapper;

import com.vipspeciall.examapplication.dto.ExamDTO;
import com.vipspeciall.examapplication.entity.Exam;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface ExamMapper {

    Exam examDTOToExam(ExamDTO examDTO);
    ExamDTO examToExamDTO(Exam exam);
}
