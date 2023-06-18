package com.example.testingsystemproject.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.testingsystemproject.models.UserAnswer;

import java.util.List;

@Dao
public interface UserAnswerDao {
    @Insert
    List<Long> insert(UserAnswer... usersAnswers);

    @Query("SELECT * FROM useranswer WHERE userId = :userId")
    List<UserAnswer> getUserAnswersIdByUserID(long userId);

    @Query("UPDATE useranswer SET userId = 1 WHERE userId = :userId ")
    void softDeleteByUserId(long userId);

    @Query("SELECT * FROM answer " +
            "JOIN useranswer ON answer.answerId = useranswer.answerId " +
            "WHERE useranswer.userId = :userId AND useranswer.questionId = :questionId LIMIT 1")
    UserAnswerResponse userAnswerTextByQuestionIdAndUserId(long userId, long questionId);

    static class UserAnswerResponse {
        public String answer;
        public boolean rightAnswer;
    }
}