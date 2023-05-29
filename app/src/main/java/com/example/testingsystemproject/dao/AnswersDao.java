package com.example.testingsystemproject.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.testingsystemproject.models.Answers;

import java.util.List;

@Dao
public interface AnswersDao {
    @Insert
    void insert(Answers... answers);

    @Query("SELECT * FROM answers WHERE question_id = :question_id")
    List<Answers> getByQuestionId(int question_id);

    @Query("SELECT * FROM answers WHERE rightAnswer = :rightAnswer")
    String getRightAnswer(boolean rightAnswer);

    @Query("SELECT * FROM answers" +
            " INNER JOIN usersanswers ON usersanswers.answer_id = answerId" +
            " INNER JOIN user ON user.userId = usersanswers.user_id" +
            " WHERE userName LIKE :userName")
    List<Answers> getUserAnswersByUserName(String userName);

    @Query("SELECT * FROM answers" +
            " INNER JOIN usersanswers ON usersanswers.answer_id = answerId" +
            " INNER JOIN user ON user.userId = usersanswers.user_id" +
            " WHERE userName LIKE :userName AND rightAnswer = :rightAnswer" )
    List<Answers> getUserRightAnswersAnswersByUserName(String userName, boolean rightAnswer);
    // Не понятно с WHERE что делать.... Правильно ли???
}
