package com.example.testingsystemproject.dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.testingsystemproject.models.User;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE userId = :id")
    User getById(int id);

    @Query("SELECT passwordHash FROM user WHERE userName = :userName")
    String getPasswordByUserName(String userName);

    @Insert
    void insert(User user);

    @Query("SELECT CAST(CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END AS BIT) FROM user WHERE email = :email;")
    boolean checkIfUserWithEmailExists(String email);

    @Query("DELETE FROM user WHERE userId = :userId")
    void deleteById(int userId);

    @Query("SELECT * FROM user WHERE userName = :userName")
    User getByUserName(String userName);
}