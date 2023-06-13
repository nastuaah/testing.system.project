package com.example.testingsystemproject.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = Answer.class, parentColumns = "answerId", childColumns = "answer_id", onDelete = ForeignKey.SET_NULL),
        @ForeignKey(entity = User.class, parentColumns = "userId", childColumns = "user_id", onDelete = ForeignKey.SET_NULL)}
)
public class UserAnswer {
    public UserAnswer(long answerId, long userId, boolean rightAnswer){
        this.answerId = answerId;
        this.userId = userId;
        this.rightAnswer = rightAnswer;
    }

    @PrimaryKey(autoGenerate = true)
    public long userAnswerId;

    @ColumnInfo(name = "answer_id")
    public long answerId;

    @ColumnInfo(name = "user_id")
    public long userId;

    @ColumnInfo(name = "rightAnswer")
    public boolean rightAnswer;
}