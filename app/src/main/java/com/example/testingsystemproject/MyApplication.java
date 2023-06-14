package com.example.testingsystemproject;

import android.app.Application;

import com.example.testingsystemproject.models.User;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class MyApplication extends Application {
    public static MyApplication instance;
    public boolean authenticated = false;
    public User user;

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
    }
}