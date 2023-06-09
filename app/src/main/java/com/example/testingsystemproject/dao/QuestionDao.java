package com.example.testingsystemproject.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.testingsystemproject.models.Question;
import com.example.testingsystemproject.models.QuestionWithAnswer;

import java.util.List;

@Dao
public interface QuestionDao {
    @Insert
    void insert(Question... questions);

    @Query("SELECT * FROM question WHERE categoryId = :categoryId ORDER BY questionId LIMIT :n")
    List<QuestionWithAnswer> getByCategoryId(long categoryId, int n);
}