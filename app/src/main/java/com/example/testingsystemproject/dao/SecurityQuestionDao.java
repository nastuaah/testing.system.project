package com.example.testingsystemproject.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.testingsystemproject.models.SecurityQuestion;
import java.util.List;

@Dao
public interface SecurityQuestionDao {
    @Query("SELECT question from SecurityQuestion")
    List<String> getAllNames();

    @Query("SELECT CASE WHEN EXISTS(SELECT 1 FROM SecurityQuestion) THEN 1 ELSE 0 END")
    boolean any();

    @Insert
    void insert(SecurityQuestion... securityQuestions);
}
