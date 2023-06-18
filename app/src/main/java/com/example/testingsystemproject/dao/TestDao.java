package com.example.testingsystemproject.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.testingsystemproject.dao.binding_types.TestWithQuestions;
import com.example.testingsystemproject.models.Test;

import java.util.List;

@Dao
public interface TestDao {
    @Insert
    Long insert(Test test);

    @Transaction
    @Query("SELECT * FROM test WHERE userId = :userId")
    List<TestWithQuestions> getTestWithAnswersByUserId(long userId);
}