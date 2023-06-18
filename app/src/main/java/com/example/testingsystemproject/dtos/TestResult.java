package com.example.testingsystemproject.dtos;

import java.util.List;

public class TestResult {
    private final long categoryId;
    private final long userId;
    private final List<Long> answerId;
    private final List<Long> questionId;
    private final List<Boolean> answeredCorrectly;
    private final int result;

    public TestResult(long categoryId, long userId, List<Long> answerId, List<Long> questionId, List<Boolean> answeredCorrectly, int result) {
        this.categoryId = categoryId;
        this.userId = userId;
        this.answerId = answerId;
        this.questionId = questionId;
        this.answeredCorrectly = answeredCorrectly;
        this.result = result;
    }

    public long getUserId() {
        return userId;
    }

    public Long getAnswerId(int i) {
        return answerId.get(i);
    }

    public Long getQuestionId(int i) {
        return questionId.get(i);
    }

    public long getCategoryId() {
        return categoryId;
    }

    public int getSize() {
        return questionId.size();
    }

    public Boolean getAnsweredCorrectly(int i) {
        return answeredCorrectly.get(i);
    }

    public int getResult() {
        return result;
    }
}