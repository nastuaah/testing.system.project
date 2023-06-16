package com.example.testingsystemproject.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = Category.class, parentColumns = "categoryId", childColumns = "categoryId", onDelete = ForeignKey.SET_NULL),
        @ForeignKey(entity = User.class, parentColumns = "userId", childColumns = "userId", onDelete = ForeignKey.SET_NULL)
})
public class Test {
    public Test(long categoryId, long userId, int result){
        this.categoryId = categoryId;
        this.userId = userId;
        this.result = result;
    }
    @PrimaryKey(autoGenerate = true)
    public long testId;

    @ColumnInfo(name = "categoryId")
    public long categoryId;

    @ColumnInfo(name = "userId")
    public long userId;

    @ColumnInfo(name = "result")
    public int result;
}