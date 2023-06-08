package com.example.testingsystemproject.dao;

import androidx.room.Dao;
import androidx.room.Insert;

import com.example.testingsystemproject.models.Question;
import com.example.testingsystemproject.models.Test;

@Dao
public interface TestDao {
    @Insert
    void insert(Test... tests);
}
