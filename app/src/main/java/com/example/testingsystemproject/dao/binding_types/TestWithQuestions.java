package com.example.testingsystemproject.dao.binding_types;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.example.testingsystemproject.models.Question;
import com.example.testingsystemproject.models.Test;
import com.example.testingsystemproject.models.TestQuestion;

import java.util.List;

public class TestWithQuestions {
    @Embedded
    public Test test;

    @Relation(
            parentColumn = "testId",
            entityColumn = "questionId",
            associateBy = @Junction(TestQuestion.class)
    )
    public List<Question> questions;
}