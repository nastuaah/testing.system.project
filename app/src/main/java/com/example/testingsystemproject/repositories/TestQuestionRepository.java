package com.example.testingsystemproject.repositories;

import com.example.testingsystemproject.dao.TestQuestionDao;
import com.example.testingsystemproject.database.AppDatabase;
import com.example.testingsystemproject.models.TestQuestion;

import javax.inject.Singleton;

@Singleton
public class TestQuestionRepository {
    private final TestQuestionDao dao;

    public TestQuestionRepository(AppDatabase appDatabase) {
        dao = appDatabase.testQuestionDao();
    }
}
