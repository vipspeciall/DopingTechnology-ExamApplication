package com.vipspeciall.examapplication.controller;

import com.vipspeciall.examapplication.dto.ExamDTO;
import com.vipspeciall.examapplication.dto.ExamStartDTO;
import com.vipspeciall.examapplication.entity.ExamSession;
import com.vipspeciall.examapplication.service.IExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exams")
public class ExamController {

    @Autowired
    private IExamService examService;

    @GetMapping
    public ResponseEntity<List<ExamDTO>> getAllExams() {
        return ResponseEntity.ok(examService.findAllExams());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExamDTO> getExamById(@PathVariable Long id) {
        ExamDTO exam = examService.findExamById(id);
        return exam != null ? ResponseEntity.ok(exam) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ExamDTO> createExam(@RequestBody ExamDTO exam) {
        return ResponseEntity.ok(examService.saveExam(exam));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExam(@PathVariable Long id) {
        examService.deleteExam(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/start")
    public ResponseEntity<String> startExam(@RequestBody ExamStartDTO examStart) {
        ExamSession session = examService.startExam(examStart.getStudentId(), examStart.getExamId());
        return ResponseEntity.ok("Exam started with session ID: " + session.getId());
    }

    @PostMapping("/continue")
    public ResponseEntity<String> continueExam(@RequestParam Long sessionId) {
        ExamSession session = examService.continueExam(sessionId);
        return ResponseEntity.ok("Exam continued for session ID: " + sessionId);
    }

    @PostMapping("/update-time")
    public ResponseEntity<String> updateTimeSpent(@RequestParam Long sessionId, @RequestParam int minutes) {
        examService.updateTimeSpent(sessionId, minutes);
        return ResponseEntity.ok("Time updated for session ID: " + sessionId);
    }
}
