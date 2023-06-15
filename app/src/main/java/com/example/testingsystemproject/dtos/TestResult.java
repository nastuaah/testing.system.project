package com.example.testingsystemproject.dtos;

import java.util.List;

public class TestResult {
    private final long categoryId;
    private final long userId;
    private final List<Long> answerId;
    private final List<Long> questionId;

    public TestResult(long categoryId, long userId, List<Long> answerId, List<Long> questionId) {
        this.categoryId = categoryId;
        this.userId = userId;
        this.answerId = answerId;
        this.questionId = questionId;
    }

    public long getUserId() {
        return userId;
    }

    public List<Long> getAnswerId() {
        return answerId;
    }

    public List<Long> getQuestionId() {
        return questionId;
    }

    public long getCategoryId() {
        return categoryId;
    }
}