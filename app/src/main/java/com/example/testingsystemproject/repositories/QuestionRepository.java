package com.example.testingsystemproject.repositories;
import com.example.testingsystemproject.dao.QuestionDao;
import com.example.testingsystemproject.database.AppDatabase;
import javax.inject.Singleton;

@Singleton
public class QuestionRepository {
    private final QuestionDao dao;
    public QuestionRepository(AppDatabase appDatabase) {
        dao = appDatabase.questionDao();
    }
}