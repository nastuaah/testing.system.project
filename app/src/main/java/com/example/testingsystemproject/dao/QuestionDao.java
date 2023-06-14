package com.example.testingsystemproject.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.testingsystemproject.models.Answer;
import com.example.testingsystemproject.models.Question;
import com.example.testingsystemproject.models.QuestionWithAnswer;

import java.util.List;

@Dao
public interface QuestionDao {
    @Insert
    long insertQuestion(Question question);
    @Query("UPDATE question SET rightAnswerId=:rightAnswerId WHERE questionId = :questionId")
    void setRightAnswer(long questionId, long rightAnswerId);
    @Insert
    long[] insertAnswers(List<Answer> answers);

    @Transaction
    @Query("SELECT * FROM question WHERE categoryId = :categoryId AND questionId NOT IN (:except) ORDER BY questionId LIMIT :n")
    List<QuestionWithAnswer> getByCategoryId(long categoryId, int n, List<Long> except);
}