package com.example.testingsystemproject.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = Category.class, parentColumns = "categoryId", childColumns = "categoryId", onDelete = ForeignKey.SET_NULL),
        @ForeignKey(entity = Answer.class, parentColumns = "answerId", childColumns = "rightAnswer", onDelete = ForeignKey.SET_NULL)
})
public class Question {
    public Question(String question,  long categoryId, long rightAnswer) {
        this.question= question;
        this.categoryId = categoryId;
        this.rightAnswer = rightAnswer;
    }

    @PrimaryKey(autoGenerate = true)
    public long questionId;

    @ColumnInfo(name = "question")
    public String question;

    @ColumnInfo(name = "categoryId")
    public long categoryId;

    @ColumnInfo(name = "rightAnswer")
    public long rightAnswer;
}