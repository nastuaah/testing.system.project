package com.example.testingsystemproject;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testingsystemproject.models.QuestionWithAnswer;
import com.example.testingsystemproject.models.User;
import com.example.testingsystemproject.repositories.UserRepository;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class UserActivity extends AppCompatActivity {

    private TextView textViewUsersLogin;
    private TextView textViewUsersPassword;
    private TextView textViewUsersName;
    private TextView textViewUsersSurname;
    private TextView textViewUsersMiddleName;
    private TextView textViewUsersDateOfBirth;
    private TextView textViewUsersEmail;
    private TextView textViewUsersMobileNumber;
    private TextView textViewUsersSecretQuestion;
    private TextView textViewUsersAnswer;



    private final User user = MyApplication.instance.user;

    @Inject
    public UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inform);

        textViewUsersLogin = findViewById(R.id.UsersLogin);
        textViewUsersPassword = findViewById(R.id.UsersPassword);
        textViewUsersName = findViewById(R.id.UsersName);
        textViewUsersSurname = findViewById(R.id.UsersSurname);
        textViewUsersMiddleName = findViewById(R.id.UsersMiddleName);
        textViewUsersDateOfBirth = findViewById(R.id.UsersDateOfBirth);
        textViewUsersEmail = findViewById(R.id.UsersEmail);
        textViewUsersMobileNumber = findViewById(R.id.UsersMobileNumber);
        textViewUsersSecretQuestion= findViewById(R.id.UsersSecretQuestion);
        textViewUsersAnswer = findViewById(R.id.UsersAnswer);

        textViewUsersLogin.setText(user.userName);
        textViewUsersPassword.setText(user.passwordHash);
        textViewUsersName.setText(user.firstName);
        textViewUsersSurname.setText(user.lastName);
        textViewUsersMiddleName.setText(user.middleName);
        textViewUsersDateOfBirth.setText((CharSequence) user.dateOfBirth);
        textViewUsersEmail.setText(user.email);
        textViewUsersMobileNumber.setText(user.mobileNumber);
        textViewUsersSecretQuestion.setText(user.securityQuestion);
        textViewUsersAnswer.setText(user.securityQuestionAnswer);

    }
}