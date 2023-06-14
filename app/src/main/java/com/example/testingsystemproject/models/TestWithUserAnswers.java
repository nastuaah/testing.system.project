package com.example.testingsystemproject.models;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class TestWithUserAnswers {
    @Embedded
    public Test test;
    @Relation(parentColumn = "userId", entityColumn = "user_id", entity = UserAnswer.class)
    public List<UserAnswer> userAnswers;
}