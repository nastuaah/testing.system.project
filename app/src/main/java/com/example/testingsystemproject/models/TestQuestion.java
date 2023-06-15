package com.example.testingsystemproject.models;

import androidx.room.Entity;

@Entity(primaryKeys = {"questionId", "testId"})
public class TestQuestion {
    private final long questionId;
    private final long testId;

    public TestQuestion(long questionId, long testId) {
        this.questionId = questionId;
        this.testId = testId;
    }

    public long getQuestionId() {
        return questionId;
    }

    public long getTestId() {
        return testId;
    }
}