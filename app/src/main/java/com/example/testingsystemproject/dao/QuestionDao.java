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
    @Query(" SELECT * from question " + " INNER JOIN category ON category.categoryId = category_id " + " WHERE questionId = :id ")
    Question getByQuestion(int id);
    @Query(" SELECT * from question WHERE option1 = :opt1 AND questionId = :id")
    String getByOption1(String opt1, int id);
    @Query("SELECT * from question WHERE option2 = :opt2 AND questionId = :id")
    String getByOption2(String opt2, int id);
    @Query("SELECT * from question WHERE option3 = :opt3 AND questionId = :id")
    String getByOption3(String opt3, int id);
    @Query("SELECT * from question WHERE answer = :answer AND questionId = :id")
    String getByAnswer(String answer, int id);
    //@Query("SELECT * from question WHERE category_id=:")
    //List<Question> getAll(int id);
    //Тань, сделай, пожалуйста, вызов вопросов по категории, чтобы вызывалось пять вопросов для
    //Квиз активити

    //метод getQuestion, чтобы вопросы вызывались по очереди, вопросы для текстового окна
    // getOption1, getOption2, getOption3 ответы для каждой кнопки опций
    //нужно еще две категории вставить
    //метод getAnswer
}
