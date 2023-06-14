package com.example.testingsystemproject;

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

    private TextView textViewUsersPassword;
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

        textViewUsersPassword = findViewById(R.id.UsersPassword);
        textViewUsersName = findViewById(R.id.UsersNamebd);
        textViewUsersResult = findViewById(R.id.LastScore);
        textViewUsersLogin = findViewById(R.id.UsersLogin);
        textViewUsersEmail = findViewById(R.id.UsersEmail);
    }
    private void showLastResult() {
        //спросить у тани в какой таблице храниться результат за тест и вписать сюда метод

    }
    public void DeleteAccount (View view){
        userRepository.deleteById(user.userId);
    }
    public void redirectToStartActivity(View view) {
        finish();
    }

}