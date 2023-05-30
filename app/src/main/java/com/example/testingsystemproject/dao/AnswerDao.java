package com.example.testingsystemproject.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.testingsystemproject.models.Answer;

import java.util.List;

@Dao
public interface AnswerDao {
    @Insert
    void insert(Answer... answers);

    @Query("SELECT * FROM answer WHERE question_id = :question_id")
    List<Answer> getByQuestionId(int question_id);

    @Query("SELECT * FROM answer WHERE rightAnswer = 1")
    String getAnswer(boolean rightAnswer);
}

