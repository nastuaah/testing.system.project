package com.example.testingsystemproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testingsystemproject.models.User;
import com.example.testingsystemproject.repositories.UserRepository;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AccountActivity extends AppCompatActivity {

    private TextView textViewUsersPassword;
    private TextView textViewUsersName;
    private TextView textViewUsersResult;
    private TextView textViewUsersLogin;
    private TextView textViewUsersEmail;

    private List<User> userList;

    private User currentPassword;
    private User currentName;

    private User currentResult;
    private User currentLogin;
    private User currentEmail;

    private User currentId;

    @Inject
    public UserRepository userRepository;
    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        textViewUsersPassword = findViewById(R.id.UsersPassword);
        textViewUsersName = findViewById(R.id.UsersNamebd);
        textViewUsersResult = findViewById(R.id.LastScore);
        textViewUsersLogin = findViewById(R.id.UsersLogin);
        textViewUsersEmail = findViewById(R.id.UsersEmail);
        userList = userRepository.getById(savedInstanceState.getInt("userId"));
        Collections.shuffle(userList);

    }

    private void showUsersPassword(){
        textViewUsersPassword.setText(currentPassword.passwordHash);
        }

    private void showUsersNickname(){
        textViewUsersName.setText(currentName.firstName);
    }
    private void showLastResult(){
        //спросить у тани в какой таблице храниться результат за тест и вписать сюда метод

    }
    private void showUsersLogin(){
        textViewUsersLogin.setText(currentLogin.userName);
    }

    private void showUsersEmail(){
        textViewUsersEmail.setText(currentEmail.email);
    }
    public void DeleteAccount (View view){
       userRepository.deleteByUserId(currentId.userId);
    }
    public void RedirectToStartActivity1(View view) {
        Intent myIntent = new Intent(this, StartActivity.class);
        startActivity(myIntent);
    }

}
