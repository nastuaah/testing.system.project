package com.example.testingsystemproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testingsystemproject.models.User;
import com.example.testingsystemproject.repositories.UserRepository;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AccountActivity extends AppCompatActivity {
    private TextView textViewUsersName;
    private TextView textViewUsersResult;
    private TextView textViewUsersLogin;
    private TextView textViewUsersEmail;

    private final User user = MyApplication.instance.user;

    @Inject
    public UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        textViewUsersEmail = findViewById(R.id.UsersEmail);
        textViewUsersName = findViewById(R.id.UsersName);
        textViewUsersResult = findViewById(R.id.LastScore);
        textViewUsersLogin = findViewById(R.id.UsersLogin);

        textViewUsersName.setText(user.firstName);
        textViewUsersLogin.setText(user.userName);
        textViewUsersEmail.setText(user.email);
        findViewById(R.id.userInfoLink)
                .setOnClickListener(this::redirectToUserInfo);
    }

    public void DeleteAccount (View view){
        userRepository.deleteById(user.userId);
    }

    public void redirectToUserInfo(View view) {
        Intent myIntent = new Intent(this, UserInfoActivity.class);
        startActivity(myIntent);
    }
    public void RedirectToTestResults(View view) {
        Intent myIntent = new Intent(this, TestResActivity.class);
        startActivity(myIntent);
    }


    public void redirectToStartActivity(View view) {
        finish();
    }
}