package com.example.testingsystemproject;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testingsystemproject.repositories.TestRepository;
import com.example.testingsystemproject.repositories.UserAnswerRepository;
import com.example.testingsystemproject.repositories.UserRepository;

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
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_result);

        textViewUsersAnswers = findViewById(R.id.UsersAnswers);
        textViewUsersScore = findViewById(R.id.UsersScore);

        int userId = MyApplication.instance.user.userId;

        textViewUsersAnswers.setText((CharSequence) userAnswerRepository.getUserAnswers(userId));
        textViewUsersScore.setText(testRepository.getUserScore(userId));

    }
}
