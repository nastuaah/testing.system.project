package com.example.testingsystemproject.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import com.example.testingsystemproject.models.Question;

@Dao
public interface QuestionDao {
    @Insert
    void insert(Question... questions);
}
