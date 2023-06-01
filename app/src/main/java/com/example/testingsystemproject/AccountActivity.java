package com.example.testingsystemproject;

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
    //добавить переменные textViewUsersNickname, textViewUsersResult
    //textViewUsersLogin, textViewUsersEmail
    private List<User> userList;
    private User currentPassword;
    // currentNickname, currentResult
    //currentLogin, currentEmail

    @Inject
    public UserRepository userRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        textViewUsersPassword = findViewById(R.id.UsersPassword);
        //добавить кнопки с логином и последним результатом textViewUsersNickname, textViewUsersResult
        // id from activity_account
        //textViewUsersLogin, textViewUsersEmail
        userList = userRepository.getById(savedInstanceState.getInt("userId"));
        Collections.shuffle(userList);

    }

    private void showUsersPassword(){
        //textViewUsersPassword.setText(currentPassword.getPassword());
        //Танин метод по вызову пароля из Юзерс бд
        // getPassword, getNickname, getResult

        //getLogin, getEmail
        }

    //Метод showUsersNickname, showLastResult, showUsersLogin, showUsersEmail
    //добавить Redirect в UsersInfo, TestResults

    public void RedirectToStartActivity1(View view) {
        Intent myIntent = new Intent(this, StartActivity.class);
        startActivity(myIntent);
    }


}
