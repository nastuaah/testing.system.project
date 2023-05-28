package com.example.testingsystemproject;

import android.content.Intent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class StartActivity extends AppCompatActivity {

    public void RedirectToAccount(View view) {
        Intent myIntent = new Intent(this, AccountActivity.class);
        startActivity(myIntent);
    }
    public void RedirectToQuiz(View view) {
        Intent myIntent = new Intent(this, QuizActivity.class);
        startActivity(myIntent);
    }
}
