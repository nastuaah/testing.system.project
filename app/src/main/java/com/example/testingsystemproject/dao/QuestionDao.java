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

    @Query("SELECT * FROM  question WHERE category_id = :categoryId ORDER BY questionId LIMIT :n")
    List<Question> getByCategoryId(int categoryId, int n);

    @Transaction
    @Query("SELECT * FROM question WHERE questionId = :questionId")
    public QuestionWithAnswer getQuestion(long questionId);

    @Transaction
    @Query("SELECT * FROM question" +
            " INNER JOIN answer" +
            " WHERE questionId = answer.question_id AND answerId = :answerId")
    public QuestionWithAnswer getOption(long answerId);
}
