package com.example.testingsystemproject.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.testingsystemproject.models.Category;
import com.example.testingsystemproject.models.Question;

import java.util.List;

@Dao
public interface QuestionDao {
    @Insert
    void insert(Question... questions);
    //@Query("SELECT * from question WHERE category_id=:")
    //List<Question> getAll();
    //Тань, сделай, пожалуйста, вызов вопросов по категории, чтобы вызывалось пять вопросов для
    //Квиз активити

    //метод getQuestion, чтобы вопросы вызывались по очереди, вопросы для текстового окна
    // getOption1, getOption2, getOption3 ответы для каждой кнопки опций
    //нужно еще две категории вставить
    //метод getAnswer
}
