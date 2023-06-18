package com.example.testingsystemproject.repositories;


import com.example.testingsystemproject.dao.CategoryDao;
import com.example.testingsystemproject.dao.TestDao;
import com.example.testingsystemproject.dao.TestQuestionDao;
import com.example.testingsystemproject.dao.UserAnswerDao;
import com.example.testingsystemproject.dao.binding_types.TestWithQuestions;
import com.example.testingsystemproject.database.AppDatabase;
import com.example.testingsystemproject.dtos.TestResult;
import com.example.testingsystemproject.dtos.UserTestResultResponse;
import com.example.testingsystemproject.models.Test;
import com.example.testingsystemproject.models.TestQuestion;
import com.example.testingsystemproject.models.UserAnswer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Singleton;

@Singleton
public class TestRepository {
    private final TestDao testDao;
    private final TestQuestionDao testQuestionDao;
    private final AppDatabase db;
    private final UserAnswerDao userAnswerDao;
    private final CategoryDao categoryDao;

    public TestRepository(AppDatabase appDatabase) {
        this.testDao = appDatabase.testDao();
        this.db = appDatabase;
        this.testQuestionDao = appDatabase.testQuestionDao();
        this.userAnswerDao = appDatabase.userAnswerDao();
        this.categoryDao = appDatabase.categoryDao();
    }
    public void SaveTestResult(TestResult testResult){
        db.runInTransaction(() -> {
            ;
            Test test = new Test(testResult.getCategoryId(), testResult.getUserId());
            testDao.insert(test);
            for (int i = 0; i < testResult.getQuestionId().size(); i++) {
                TestQuestion testQuestion = new TestQuestion(testResult.getCategoryId(), testResult.getQuestionId().get(i));
                testQuestionDao.insert(testQuestion);
            }
            for (int i = 0; i < testResult.getAnswerId().size(); i++) {
                UserAnswer userAnswer = new UserAnswer(testResult.getUserId(), testResult.getAnswerId().get(i), testResult.getAnsweredCorrectly().get(i));
                userAnswerDao.insert(userAnswer);
            }
        });
    }

    public List<UserTestResultResponse> getUserTestResults(long userId) {
        List<TestWithQuestions> testsWithQuestions = testDao.getTestWithAnswersByUserId(userId);
        ArrayList<UserTestResultResponse> resultResponses = categoryDao.getAll().stream().map(x -> new UserTestResultResponse(x.getName())).collect(Collectors.toCollection(ArrayList::new));
        for (TestWithQuestions test: testsWithQuestions) {
            List<UserAnswerDao.UserAnswerResponse> answers = test.questions.stream().map(x -> this.userAnswerDao.userAnswerTextByQuestionIdAndUserId(userId, x.questionId)).collect(Collectors.toList());
            for (int i = 0; i < test.questions.size(); i++) {
                resultResponses.get((int) test.test.categoryId)
                        .questionAnswerResults
                        .add(new UserTestResultResponse.QuestionAnswerResult(test.questions.get(i).question, answers.get(i).answer, answers.get(i).rightAnswer));
            }
        }
        return resultResponses;
    }
}