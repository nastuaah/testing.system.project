package com.example.testingsystemproject.dao;

import androidx.room.Dao;
import androidx.room.Insert;

import com.example.testingsystemproject.models.TestQuestion;

import java.util.List;

@Dao
public interface TestQuestionDao {
    @Insert
    void insert(List<TestQuestion> testQuestions);
}
