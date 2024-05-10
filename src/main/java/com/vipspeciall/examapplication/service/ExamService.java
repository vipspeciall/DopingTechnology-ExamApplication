package com.vipspeciall.examapplication.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vipspeciall.examapplication.dto.ExamDTO;
import com.vipspeciall.examapplication.entity.Exam;
import com.vipspeciall.examapplication.entity.ExamResult;
import com.vipspeciall.examapplication.entity.ExamSession;
import com.vipspeciall.examapplication.mapper.ExamMapper;
import com.vipspeciall.examapplication.repository.ExamRepository;
import com.vipspeciall.examapplication.repository.ExamSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExamService implements IExamService {

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ExamMapper examMapper;

    @Autowired
    private ExamSessionRepository sessionRepository;

    public List<ExamDTO> findAllExams() {
        return examRepository.findAll()
                .stream()
                .map(exam -> examMapper.examToExamDTO(exam))
                .collect(Collectors.toUnmodifiableList());
    }

    public ExamDTO findExamById(Long id) {
        return examMapper
                .examToExamDTO(examRepository
                        .findById(id)
                        .orElse(null));
    }

    public ExamDTO saveExam(ExamDTO examDTO) {
        Exam exam = examMapper.examDTOToExam(examDTO);
        return examMapper.
                examToExamDTO(examRepository.save(exam));
    }

    public void deleteExam(Long id) {
        examRepository.deleteById(id);
    }

    @Override
    public void submitExamResults(ExamResult examResult) {

    }

    public ExamSession startExam(Long studentId, Long examId) {
        ExamSession session = new ExamSession();
        session.setStudentId(studentId);
        session.setExamId(examId);
        session.setStartTime(LocalDateTime.now());
        session.setCurrentAnswers("[]");
        return sessionRepository.save(session);
    }

    public ExamSession continueExam(Long sessionId) {
        ExamSession session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found"));
        if (session.getContinuationsUsed() >= session.getTotalAllowedContinuations()) {
            throw new IllegalStateException("No more continuations allowed for this exam.");
        }
        if (!session.getIsCompleted()) {
            session.setContinuationsUsed(session.getContinuationsUsed() + 1);
            sessionRepository.save(session);
            return session;
        } else {
            throw new IllegalStateException("This exam session is already completed.");
        }
    }

    public void updateTimeSpent(Long sessionId, int additionalMinutes) {
        ExamSession session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found"));
        if (!session.getIsCompleted()) {
            session.setTimeSpent(session.getTimeSpent() + additionalMinutes);
            sessionRepository.save(session);
        }
    }

    public void submitExam(Long sessionId) {
        ExamSession session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found"));
        if (session.getIsCompleted()) {
            throw new IllegalStateException("Exam is already submitted.");
        }

        session.setEndTime(LocalDateTime.now());
        session.setIsCompleted(true);
        sessionRepository.save(session);

        ExamResult examResult = calculateExamResult(session); // Skor hesaplama ve diğer işlemler burada yapılır
        String resultData = new Gson().toJson(examResult);
        kafkaTemplate.send("exam-results", resultData);
        System.out.println("Exam submitted for session ID: " + sessionId);
    }

    private ExamResult calculateExamResult(ExamSession session) {
        // Skor hesaplama ve diğer işlemler
        // Örnek: Her doğru cevap için 1 puan, toplam puan hesaplama
        int score = calculateScore(session.getCurrentAnswers(), session.getExamId());
        return new ExamResult(session.getStudentId(), session.getExamId(), score);
    }

    private int calculateScore(String currentAnswersJson, Long examId) {
        // JSON string'ini cevap listesine çevir ve skor hesapla
        Type type = new TypeToken<List<String>>(){}.getType();
        List<String> answers = new Gson().fromJson(currentAnswersJson, type);
        int correctCount = 0;
        // Burada, her bir cevabın doğruluğunu kontrol et (örnek: veritabanından doğru cevapları çek)
        return correctCount;
    }


}
