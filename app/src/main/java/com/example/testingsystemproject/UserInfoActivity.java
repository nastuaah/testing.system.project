package com.example.testingsystemproject;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testingsystemproject.models.User;
import com.example.testingsystemproject.repositories.UserRepository;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class UserInfoActivity extends AppCompatActivity {
    private TextInputLayout userName;
    private TextInputLayout firstName;
    private TextInputLayout middleName;
    private TextInputLayout lastName;
    private TextInputLayout dateOfBirth;
    private TextInputLayout email;
    private TextInputLayout mobileNumber;
    private TextInputLayout securityQuestionAnswer;
    private final User user = MyApplication.instance.user;

    @Inject
    public UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inform);

        userName = findViewById(R.id.userUserName);
        firstName = findViewById(R.id.userFirstName);
        middleName = findViewById(R.id.userMiddleName);
        lastName = findViewById(R.id.userLastName);
        dateOfBirth = findViewById(R.id.userDateOfBirth);
        email = findViewById(R.id.userEmail);
        mobileNumber = findViewById(R.id.userMobileNumber);
        securityQuestionAnswer = findViewById(R.id.userSecurityQuestionAnswer);
        findViewById(R.id.btnBackToAccount)
                .setOnClickListener(v -> finish());

        Objects.requireNonNull(userName.getEditText()).setText(user.userName);
        Objects.requireNonNull(firstName.getEditText()).setText(user.firstName);
        Objects.requireNonNull(lastName.getEditText()).setText(user.lastName);
        Objects.requireNonNull(middleName.getEditText()).setText(user.middleName);
        Objects.requireNonNull(dateOfBirth.getEditText()).setText(user.dateOfBirth.toString());
        Objects.requireNonNull(email.getEditText()).setText(user.email);
        Objects.requireNonNull(mobileNumber.getEditText()).setText(user.mobileNumber);
        Objects.requireNonNull(securityQuestionAnswer.getEditText()).setText(user.securityQuestionAnswer);
    }
}