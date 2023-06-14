package com.example.testingsystemproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class StartActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }
    public void RedirectToAccount(View view) {
        Intent myIntent = new Intent(this, AccountActivity.class);
        startActivity(myIntent);
    }
    public void RedirectToOptionActivity(View view) {
        Intent myIntent = new Intent(this, OptionActivity.class);
        startActivity(myIntent);
    }
}