package com.example.testingsystemproject.repositories;

import com.example.testingsystemproject.dao.TestQuestionDao;
import com.example.testingsystemproject.database.AppDatabase;
import com.example.testingsystemproject.models.TestQuestion;

import java.util.List;

import javax.inject.Singleton;

@Singleton
public class TestQuestionRepository {
    private final TestQuestionDao testQuestionDao;

    public TestQuestionRepository(AppDatabase appDatabase) {
        testQuestionDao = appDatabase.testQuestionDao();
    }
    public void insert(TestQuestion... testQuestions){testQuestionDao.insert(testQuestions);}

    public List<TestQuestion> getTestQuestionByTestId(int testId){
        return testQuestionDao.getTestQuestionsByTestId(testId);
    }
}
