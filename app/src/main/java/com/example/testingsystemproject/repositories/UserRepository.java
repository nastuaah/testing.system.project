package com.example.testingsystemproject.repositories;

import com.example.testingsystemproject.models.User;

import com.example.testingsystemproject.dao.UserDao;
import com.example.testingsystemproject.database.AppDatabase;
import com.example.testingsystemproject.helpers.EncryptionHelper;
import com.example.testingsystemproject.models.User;
import java.util.Objects;
import javax.inject.Singleton;

@Singleton
public class UserRepository {
    private final UserDao userDao;

    public UserRepository(AppDatabase appDatabase) {
        this.userDao = appDatabase.userDao();
    }
    public boolean checkIfUserWithEmailExists(String email) {
        return userDao.checkIfUserWithEmailExists(email);
    }

    public void insert(User user) {
        userDao.insert(user);
    }

    public boolean authenticate(String userName, String password) {
        String passwordHashFromDatabase = userDao.getPasswordByUserName(userName);
        return Objects.equals(passwordHashFromDatabase, EncryptionHelper.toSHA256String(password));
    }
}