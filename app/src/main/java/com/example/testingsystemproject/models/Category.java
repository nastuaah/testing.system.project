package com.example.testingsystemproject.models;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Category {
    public static final int ANIMALS = 1;
    public static final int PEOPLE = 2;
    public static final int RANDOM = 3;
    public static final int MUSIC = 4 ;
    public static final int SPORT = 5;

    @PrimaryKey(autoGenerate = true)
    private int categoryId;
    @ColumnInfo(name = "name")
    private String name;

    public Category(String name) {
        this.name = name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int id) {
        this.categoryId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NonNull
    @Override
    public String toString() {
        return getName();
    }
}
