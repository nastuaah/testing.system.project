package com.example.testingsystemproject.repositories;

import com.example.testingsystemproject.dao.UserAnswerDao;
import com.example.testingsystemproject.database.AppDatabase;

import javax.inject.Singleton;

@Singleton
public class UserAnswerRepository {
        private final UserAnswerDao dao;
        public UserAnswerRepository(AppDatabase appDatabase) {dao = appDatabase.userAnswerDao();}

}

