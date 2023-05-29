package com.example.testingsystemproject.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.testingsystemproject.models.UsersAnswers;

import java.util.List;

@Dao
public interface UsersAnswersDao {
    @Insert
    void insert(UsersAnswers... usersAnswers);

    @Query("SELECT * FROM usersanswers WHERE user_id = :user_id")
    List<UsersAnswers> getUserAnswersIdByUserID(int user_id);
}
