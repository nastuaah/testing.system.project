package com.example.testingsystemproject.repositories;

import com.example.testingsystemproject.dao.UserAnswerDao;
import com.example.testingsystemproject.database.AppDatabase;
import com.example.testingsystemproject.models.UserAnswer;

import java.util.List;

import javax.inject.Singleton;

@Singleton
public class UserAnswerRepository {
        private final UserAnswerDao userAnswerDao;
        public UserAnswerRepository(AppDatabase appDatabase) {userAnswerDao = appDatabase.userAnswerDao();}
        public int updateByUserId(int n, int userId){return userAnswerDao.updateByUserId(n,userId);}

        public List<UserAnswer> getUserAnswersIdByUserID(int userId){
                return userAnswerDao.getUserAnswersIdByUserID(userId);
        }
}