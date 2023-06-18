package com.example.testingsystemproject.models;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class TextUserAnswer {
    @Embedded
    public Answer answer;
    @Embedded
    public Question question;
    @Relation(parentColumn = "answerId", entityColumn = "answerId", entity = UserAnswer.class)
    public List<UserAnswer> userAnswers;
    @Relation(parentColumn = "questionId", entityColumn = "questionId", entity = Answer.class)
    public List<Answer> answers;
}
