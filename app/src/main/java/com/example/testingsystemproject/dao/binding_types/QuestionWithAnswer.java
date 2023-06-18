package com.example.testingsystemproject.dao.binding_types;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.testingsystemproject.models.Answer;
import com.example.testingsystemproject.models.Question;

import java.util.List;

public class QuestionWithAnswer {
    @Embedded
    public Question question;

    @Relation(parentColumn = "questionId", entityColumn = "question_id", entity = Answer.class)
    public List<Answer> answers;

}