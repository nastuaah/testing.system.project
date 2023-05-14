package com.example.testingsystemproject.dao;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface QuestionDao {
    @Insert
    void insert(Question... questions);
}
