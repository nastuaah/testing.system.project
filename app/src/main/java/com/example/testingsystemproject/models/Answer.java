package com.example.testingsystemproject.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = Question.class, parentColumns = "questionId", childColumns = "question_id", onDelete = ForeignKey.SET_NULL)}
)

public class Answer {
    public Answer(String answer, Boolean rightAnswer, long question_id){
        this.answer = answer;
        this.rightAnswer = rightAnswer;
        this.question_id = question_id;
    }

    @PrimaryKey(autoGenerate = true)
    public long answerId;

    @ColumnInfo(name = "answer")
    public String answer;

    @ColumnInfo(name = "rightAnswer")
    public Boolean rightAnswer;

    @ColumnInfo(name = "question_id")
    public long question_id;
}
