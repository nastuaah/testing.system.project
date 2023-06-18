package com.example.testingsystemproject.repositories;

import com.example.testingsystemproject.dao.UserAnswerDao;
import com.example.testingsystemproject.database.AppDatabase;

import javax.inject.Singleton;

@Singleton
public class UserAnswerRepository {
        private final UserAnswerDao userAnswerDao;
        public UserAnswerRepository(AppDatabase appDatabase) {
                userAnswerDao = appDatabase.userAnswerDao();
        }

        public void softDeleteUser(long userId) {
                userAnswerDao.softDeleteByUserId(userId);
        }
}