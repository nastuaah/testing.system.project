package com.example.testingsystemproject.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.testingsystemproject.dao.CategoryDao;
import com.example.testingsystemproject.dao.QuestionDao;
import com.example.testingsystemproject.dao.SecurityQuestionDao;
import com.example.testingsystemproject.dao.UserDao;
import com.example.testingsystemproject.models.Category;
import com.example.testingsystemproject.models.Question;
import com.example.testingsystemproject.models.SecurityQuestion;
import com.example.testingsystemproject.models.User;

@Database(entities = { User.class, SecurityQuestion.class, Question.class, Category.class }, version = 1)
@TypeConverters({ DateConverter.class })
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract QuestionDao questionDao();
    public abstract SecurityQuestionDao securityQuestionDao();
    public abstract CategoryDao categoryDao();
}
