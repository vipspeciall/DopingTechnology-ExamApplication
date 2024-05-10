package com.vipspeciall.examapplication.consumer;

import com.google.gson.Gson;
import com.vipspeciall.examapplication.entity.ExamResult;
import com.vipspeciall.examapplication.repository.ExamResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

public class ExamResultConsumer {

    @Autowired
    private ExamResultRepository examResultRepository;


    @KafkaListener(topics = "exam-results", groupId = "exam-results-group")
    public void listenExamResults(String message) {
        Gson gson = new Gson();
        ExamResult examResult = gson.fromJson(message, ExamResult.class);

        processExamResult(examResult);
    }

    private void processExamResult(ExamResult examResult) {
        System.out.println("Received exam result for student ID: " + examResult.getStudentId());
        System.out.println("Exam ID: " + examResult.getExamId());
        System.out.println("Score: " + examResult.getScore());
        System.out.println("Total Time Spent: " + examResult.getTimeSpent() + " minutes");

        // Örnek: ExamResult'ı veritabanına kaydetme
        saveExamResult(examResult);
    }

    private void saveExamResult(ExamResult examResult) {
        // Veritabanına kaydetme işlemi
        examResultRepository.save(examResult);
        System.out.println("Exam result saved for student ID: " + examResult.getStudentId());
    }
}
