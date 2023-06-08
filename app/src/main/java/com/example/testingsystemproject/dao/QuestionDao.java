package com.example.testingsystemproject.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.testingsystemproject.models.Category;
import com.example.testingsystemproject.models.Question;
import com.example.testingsystemproject.models.QuestionWithAnswer;

import java.util.List;

@Dao
public interface QuestionDao {
    @Insert
    void insert(Question... questions);

    @Transaction
    @Query("SELECT * FROM question WHERE categoryId = :categoryId ORDER BY questionId LIMIT :n")
    public QuestionWithAnswer getQuestion(long categoryId, int n);

}
