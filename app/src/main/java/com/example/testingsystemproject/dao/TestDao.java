package com.example.testingsystemproject.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.testingsystemproject.models.Test;
import com.example.testingsystemproject.models.TestWithUserAnswer;

import java.util.List;

@Dao
public interface TestDao {
    @Insert
    void insert(Test... tests);

    @Query("SELECT * FROM test WHERE userId = :userId")
    List<Test> getTestByUserId(int userId);

    @Query("SELECT * FROM test WHERE userId = :userId")
    List<TestWithUserAnswer> getTestWithUserAnswerByUserId(int userId);
}
