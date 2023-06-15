package com.example.testingsystemproject.repositories;

import com.example.testingsystemproject.dao.TestDao;
import com.example.testingsystemproject.dao.TestQuestionDao;
import com.example.testingsystemproject.dao.UserAnswerDao;
import com.example.testingsystemproject.database.AppDatabase;
import com.example.testingsystemproject.dtos.TestResult;
import com.example.testingsystemproject.models.Test;
import com.example.testingsystemproject.models.TestQuestion;
import com.example.testingsystemproject.models.TestWithUserAnswers;
import com.example.testingsystemproject.models.UserAnswer;

import java.util.List;

import javax.inject.Singleton;

@Singleton
public class TestRepository {
    private TestDao testDao;
    private TestQuestionDao testQuestionDao;
    private UserAnswerDao userAnswerDao;

    public TestRepository(AppDatabase appDatabase) {
        this.testDao = appDatabase.testDao();
        this.testQuestionDao = appDatabase.testQuestionDao();
        this.userAnswerDao = appDatabase.userAnswerDao();
    }
    public List<TestWithUserAnswers> getTestWithUserAnswerByUserId(int userId){
        return testDao.getTestWithUserAnswerByUserId(userId);
    }
    public void SaveTestResult(TestResult testResult){
        Test test = new Test(testResult.getCategoryId(), testResult.getUserId());
        testDao.insert(test);
        for (int i=0; i < testResult.getQuestionId().size(); i++) {
            TestQuestion testQuestion = new TestQuestion(testResult.getCategoryId(), testResult.getQuestionId().get(i));
            testQuestionDao.insert(testQuestion);
        }
        for (int i=0; i < testResult.getAnswerId().size(); i++) {
            UserAnswer userAnswer = new UserAnswer(testResult.getUserId(), testResult.getAnswerId().get(i), testResult.getAnsweredCorrectly().get(i));
            userAnswerDao.insert(userAnswer);
        }
    }
}