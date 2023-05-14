package com.example.testingsystemproject.repositories;

import com.example.testingsystemproject.dao.SecurityQuestionDao;
import com.example.testingsystemproject.database.AppDatabase;
import java.util.List;
import javax.inject.Singleton;

@Singleton
public class SecurityQuestionRepository {
    private final SecurityQuestionDao dao;
    public SecurityQuestionRepository(AppDatabase appDatabase) {
        dao = appDatabase.securityQuestionDao();
    }

    public List<String> getAllNames() {
        return dao.getAllNames();
    }
}
