package com.example.testingsystemproject.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.testingsystemproject.models.Answers;

import java.util.List;

@Dao
public interface AnswersDao {
    @Insert
    void insert(Answers... answers);

    @Query("SELECT * from question WHERE category_id = :categoryId ORDER BY questionId LIMIT :n")
    List<Question> getByCategoryId(int categoryId, int n);

    List<Question> getByCategoryId(int categoryId);
}
