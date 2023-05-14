package com.example.testingsystemproject.repositories;

import com.example.testing_system.dao.SecurityQuestionDao;
import com.example.testing_system.database.AppDatabase;
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
