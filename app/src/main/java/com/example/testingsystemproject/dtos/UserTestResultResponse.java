package com.example.testingsystemproject.dtos;

import java.util.ArrayList;
import java.util.List;

public class UserTestResultResponse {
    public UserTestResultResponse(String category) {
        this.category = category;
    }

    public String category;
    public List<QuestionAnswerResult> questionAnswerResults = new ArrayList<>();

    public static class QuestionAnswerResult {
        public QuestionAnswerResult(String question, String answer, boolean correctAnswer) {
            this.question = question;
            this.answer = answer;
            this.correctAnswer = correctAnswer;
        }

        public String question;
        public String answer;
        public boolean correctAnswer;
    }
}