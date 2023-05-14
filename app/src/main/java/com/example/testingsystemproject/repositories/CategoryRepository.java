package com.example.testingsystemproject.repositories;

import com.example.testingsystemproject.dao.CategoryDao;
import com.example.testingsystemproject.database.AppDatabase;
import com.example.testingsystemproject.models.Category;
import java.util.List;
import javax.inject.Singleton;

@Singleton
public class CategoryRepository {
    private final CategoryDao dao;
    public CategoryRepository(AppDatabase appDatabase) {
        dao = appDatabase.categoryDao();
    }

    public List<Category> getAll() {
        return dao.getAll();
    }
}
