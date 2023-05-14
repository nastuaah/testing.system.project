package com.example.testingsystemproject.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.testing_system.dao.CategoryDao;
import com.example.testing_system.dao.QuestionDao;
import com.example.testing_system.dao.SecurityQuestionDao;
import com.example.testing_system.dao.UserDao;
import com.example.testing_system.models.Category;
import com.example.testing_system.models.Question;
import com.example.testing_system.models.SecurityQuestion;
import com.example.testing_system.models.User;

@Database(entities = { User.class, SecurityQuestion.class, Question.class, Category.class }, version = 1)
@TypeConverters({ DateConverter.class })
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract QuestionDao questionDao();
    public abstract SecurityQuestionDao securityQuestionDao();
    public abstract CategoryDao categoryDao();
}
