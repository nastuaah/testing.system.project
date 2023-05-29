package com.example.testingsystemproject.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.testingsystemproject.models.Category;
import com.example.testingsystemproject.models.Question;

import java.util.List;

@Dao
public interface QuestionDao {
    @Insert
    void insert(Question... questions);
    @Query("SELECT * from question WHERE category_id = :categoryId ORDER BY questionId LIMIT :n")
    List<Question> getByCategoryId(int categoryId, int n);

    List<Question> getByCategoryId(int categoryId);
}
