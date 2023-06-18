package com.example.testingsystemproject;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testingsystemproject.repositories.TestRepository;
import com.example.testingsystemproject.repositories.UserAnswerRepository;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class TestResActivity extends AppCompatActivity {

    @Inject
    public TestRepository testRepository;
    @Inject
    public UserAnswerRepository userAnswerRepository;

    private TextView textViewUsersAnswers;
    private TextView textViewUsersScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_result);

        textViewUsersAnswers = findViewById(R.id.UsersAnswers);
        textViewUsersScore = findViewById(R.id.UsersScore);
    }
}