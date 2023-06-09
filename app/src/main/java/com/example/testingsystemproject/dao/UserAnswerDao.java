package com.example.testingsystemproject.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.testingsystemproject.models.UserAnswer;

import java.util.List;

@Dao
public interface UserAnswerDao {
    @Insert
    void insert(UserAnswer... usersAnswers);

    @Query("SELECT * FROM useranswer WHERE user_id = :user_id")
    List<UserAnswer> getUserAnswersIdByUserID(int user_id);

    @Query("UPDATE useranswer SET user_id = :n WHERE user_id = :userId ")
    int updateByUserId(int n, int userId);
}
