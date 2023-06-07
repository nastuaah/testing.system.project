package com.example.testingsystemproject.models;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.Query;
import androidx.room.Relation;

import java.util.List;

@Entity(foreignKeys = {
        @ForeignKey(entity = Category.class, parentColumns = "categoryId", childColumns = "category_id", onDelete = ForeignKey.SET_NULL)}
)
public class Question {
    public Question(String question,  long category_id) {
        this.question= question;
        this.category_id = category_id;
    }

    @PrimaryKey(autoGenerate = true)
    public long questionId;

    @ColumnInfo(name = "question")
    public String question;

    @ColumnInfo(name = "category_id")
    public long category_id;

    @Relation(parentColumn = "questionId", entityColumn = "question_id", entity = Answer.class)
    public List<Answer> answers;

    }