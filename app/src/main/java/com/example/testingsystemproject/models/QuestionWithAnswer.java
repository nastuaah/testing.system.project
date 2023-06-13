package com.example.testingsystemproject.models;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class QuestionWithAnswer {
    @Embedded
    public Question question;

    @Relation(parentColumn = "questionId", entityColumn = "question_id", entity = Answer.class)
    public List<Answer> answers;

}
