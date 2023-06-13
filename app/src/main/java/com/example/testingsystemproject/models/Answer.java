package com.example.testingsystemproject.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = Question.class, parentColumns = "questionId", childColumns = "question_id", onDelete = ForeignKey.SET_NULL),
        @ForeignKey(entity = Category.class, parentColumns = "categoryId", childColumns = "categoryId", onDelete = ForeignKey.SET_NULL)
})
public class Answer {
    public Answer(String answer, long questionId, long categoryId){
        this.answer = answer;
        this.questionId = questionId;
        this.categoryId = categoryId;
    }

    @PrimaryKey(autoGenerate = true)
    public long answerId;

    @ColumnInfo(name = "answer")
    public String answer;

    @ColumnInfo(name = "question_id")
    public long questionId;

    @ColumnInfo(name = "categoryId")
    public long categoryId;
}