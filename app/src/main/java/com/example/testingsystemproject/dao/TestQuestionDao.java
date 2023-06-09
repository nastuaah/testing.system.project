package com.example.testingsystemproject.dao;

import androidx.room.Dao;
import androidx.room.Insert;

import com.example.testingsystemproject.models.TestQuestion;

@Dao
public interface TestQuestionDao {
    @Insert
    void insert(TestQuestion... testQuestions);
}