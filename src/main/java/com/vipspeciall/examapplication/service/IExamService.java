package com.vipspeciall.examapplication.service;

import com.vipspeciall.examapplication.dto.ExamDTO;
import com.vipspeciall.examapplication.entity.ExamResult;
import com.vipspeciall.examapplication.entity.ExamSession;

import java.util.List;

public interface IExamService {
    List<ExamDTO> findAllExams();
    ExamDTO findExamById(Long id);
    ExamDTO saveExam(ExamDTO exam);
    void deleteExam(Long id);
    void submitExamResults(ExamResult examResult);
    ExamSession startExam(Long studentId, Long examId);
    ExamSession continueExam(Long sessionId);
    void updateTimeSpent(Long sessionId, int additionalMinutes);
    void submitExam(Long sessionId);
}
