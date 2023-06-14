package com.example.testingsystemproject.repositories;

import com.example.testingsystemproject.dao.TestDao;
import com.example.testingsystemproject.database.AppDatabase;
import com.example.testingsystemproject.models.Test;
import com.example.testingsystemproject.models.TestWithUserAnswers;

import java.util.List;

import javax.inject.Singleton;

@Singleton
public class TestRepository {
    private final TestDao testDao;

    public TestRepository(AppDatabase appDatabase) {
        this.testDao = appDatabase.testDao();
    }
    public void insert(Test... tests){testDao.insert(tests);}
    public List<Test> getTestByUserId(int userId){
        return testDao.getTestByUserId(userId);
    }
    public List<TestWithUserAnswers> getTestWithUserAnswerByUserId(int userId){
        return testDao.getTestWithUserAnswerByUserId(userId);
    }
}