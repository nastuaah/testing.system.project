package com.example.testingsystemproject.repositories;

import com.example.testingsystemproject.dao.TestDao;
import com.example.testingsystemproject.database.AppDatabase;

import javax.inject.Singleton;

@Singleton
public class TestRepository {
    private final TestDao dao;

    public TestRepository(AppDatabase appDatabase) {
        dao = appDatabase.testDao();
    }
}
