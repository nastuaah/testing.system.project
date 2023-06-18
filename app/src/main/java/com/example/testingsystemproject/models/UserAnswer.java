package com.example.testingsystemproject.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = Answer.class, parentColumns = "answerId", childColumns = "answerId", onDelete = ForeignKey.SET_NULL),
        @ForeignKey(entity = User.class, parentColumns = "userId", childColumns = "userId", onDelete = ForeignKey.SET_NULL),
        @ForeignKey(entity = Question.class, parentColumns = "questionId", childColumns = "questionId", onDelete = ForeignKey.SET_NULL)}
)
public class UserAnswer {
    public UserAnswer(long answerId, long userId, boolean rightAnswer){
        this.answerId = answerId;
        this.userId = userId;
        this.rightAnswer = rightAnswer;
    }

    @PrimaryKey(autoGenerate = true)
    public long userAnswerId;

    @ColumnInfo(name = "answerId")
    public long answerId;

    @ColumnInfo(name = "userId")
    public long userId;

    @ColumnInfo(name = "questionId")
    public long questionId;

    @ColumnInfo(name = "rightAnswer")
    public boolean rightAnswer;
}