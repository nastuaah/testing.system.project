package com.example.testingsystemproject.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = Answer.class, parentColumns = "answerId", childColumns = "answer_id", onDelete = ForeignKey.SET_NULL),
        @ForeignKey(entity = User.class, parentColumns = "userId", childColumns = "user_id", onDelete = ForeignKey.SET_NULL)}
)

public class UserAnswer {
    public UserAnswer(long answer_id, long user_id){
        this.answer_id = answer_id;
        this.user_id = user_id;
    }
    @ColumnInfo(name = "answer_id")
    public long answer_id;

    @ColumnInfo(name = "user_id")
    public long user_id;
}
