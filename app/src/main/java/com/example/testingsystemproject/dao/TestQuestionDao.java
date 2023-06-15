package com.example.testingsystemproject.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.testingsystemproject.models.TestQuestion;

import java.util.List;

@Dao
public interface TestQuestionDao {
    @Insert
    List<Long> insert(TestQuestion... testQuestions);

    @Query("SELECT * FROM testquestion WHERE testId = :testId")
    List<TestQuestion> getTestQuestionsByTestId(int testId);

}