package com.example.testingsystemproject.repositories;

import com.example.testingsystemproject.dao.AnswerDao;
import com.example.testingsystemproject.database.AppDatabase;

import javax.inject.Singleton;

@Singleton
public class AnswerRepository {
    private final AnswerDao dao;
    public AnswerRepository(AppDatabase appDatabase) {
        dao = appDatabase.answerDao();
    }

}
