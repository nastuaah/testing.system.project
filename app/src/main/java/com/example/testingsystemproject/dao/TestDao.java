package com.example.testingsystemproject.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.testingsystemproject.models.Test;
import com.example.testingsystemproject.models.TestWithUserAnswers;

import java.util.List;

@Dao
public interface TestDao {
    @Insert
    void insert(Test... tests);

    @Query("SELECT * FROM test WHERE userId = :userId")
    List<Test> getTestByUserId(int userId);

    @Transaction
    @Query("SELECT * FROM test WHERE userId = :userId")
    List<TestWithUserAnswers> getTestWithUserAnswerByUserId(int userId);
}