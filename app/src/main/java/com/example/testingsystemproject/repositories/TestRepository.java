package com.example.testingsystemproject.repositories;

import com.example.testingsystemproject.dao.TestDao;
import com.example.testingsystemproject.database.AppDatabase;
import com.example.testingsystemproject.models.Test;

import javax.inject.Singleton;

@Singleton
public class TestRepository {
    private final TestDao testDao;

    public TestRepository(AppDatabase appDatabase) {
        this.testDao = appDatabase.testDao();
    }
    public void insert(Test... tests){testDao.insert(tests);}
}
