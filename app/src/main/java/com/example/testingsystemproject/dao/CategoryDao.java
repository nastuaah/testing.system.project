package com.example.testingsystemproject.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.testingsystemproject.models.Category;
import java.util.List;

@Dao
public interface CategoryDao {
    @Insert
    void insert(Category... questions);
    @Query("SELECT * from category")
    List<Category> getAll();
}
