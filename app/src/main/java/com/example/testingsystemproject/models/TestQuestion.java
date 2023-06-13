package com.example.testingsystemproject.models;

import androidx.room.Entity;

@Entity(primaryKeys = {"questionId", "testId"})
public class TestQuestion {
    public long questionId;
    public long testId;
}
