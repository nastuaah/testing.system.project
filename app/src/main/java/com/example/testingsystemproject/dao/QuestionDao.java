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
    @Query("SELECT * FROM  question WHERE category_id = :categoryId ORDER BY questionId LIMIT :n")
    List<Question> getByCategoryId(int categoryId, int n);

    @Query("SELECT option1 FROM question WHERE question = :questionId")
    String getOption1(int questionId);

    @Query("SELECT option2 FROM question WHERE questionId = :questionId")
    String getOption2(int questionId);

    @Query("SELECT option3 FROM question WHERE questionId = :questionId")
    String getOption3(int questionId);
}
