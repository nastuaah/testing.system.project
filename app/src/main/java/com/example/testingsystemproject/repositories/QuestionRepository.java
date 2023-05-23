package com.example.testingsystemproject.repositories;
import com.example.testingsystemproject.dao.QuestionDao;
import com.example.testingsystemproject.database.AppDatabase;
import com.example.testingsystemproject.models.Question;

import java.util.List;

import javax.inject.Singleton;

@Singleton
public class QuestionRepository {
    private final QuestionDao dao;
    public QuestionRepository(AppDatabase appDatabase) {
        dao = appDatabase.questionDao();
    }
    public List<Question> getByCategoryId(int categoryId){
        return dao.getByCategoryId(categoryId);
    }
}