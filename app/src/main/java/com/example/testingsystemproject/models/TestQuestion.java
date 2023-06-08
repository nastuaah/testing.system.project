package com.example.testingsystemproject.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class TestQuestion {

    @PrimaryKey(autoGenerate = true)
    public long question_Id;

    @PrimaryKey(autoGenerate = true)
    public long testId;
}
