package com.example.testingsystemproject.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = Question.class, parentColumns = "questionId", childColumns = "question_Id", onDelete = ForeignKey.SET_NULL),
        @ForeignKey(entity = Category.class, parentColumns = "categoryId", childColumns = "testId", onDelete = ForeignKey.SET_NULL)
},
        indices = {@Index(value = {"question_Id"},
        unique = true)})
public class TestQuestion {
    public TestQuestion(long question_Id){
        this.question_Id = question_Id;
    }

    @ColumnInfo(name = "questionId")
    public long question_Id;

    @PrimaryKey(autoGenerate = true)
    public long testId;
}
