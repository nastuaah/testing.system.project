package com.example.testingsystemproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testingsystemproject.models.User;
import com.example.testingsystemproject.repositories.UserRepository;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    @Inject
    UserRepository repository;
    private TextInputLayout Login;
    private TextInputLayout Password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Login = findViewById(R.id.textInputEmail);
        Password = findViewById(R.id.textInputPassword);
        Button loginBtn = findViewById(R.id.idBtnLogin);
        loginBtn.setOnClickListener(this::login);
    }

    private void login(View view) {
        String userName = Login.getEditText().getText().toString();
        String password = Password.getEditText().getText().toString();
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) return;
        User user = repository.authenticate(userName, password);
        if (user != null) {
            MyApplication app = (MyApplication) this.getApplication();
            app.authenticated = true;
            app.user = user;
            Intent i = new Intent(this, StartActivity.class);
            startActivity(i);
            finish();
            return;
        }
        Snackbar.make(view, "Неверный логин и/или пароль", Snackbar.LENGTH_SHORT).show();
    }

    public void redirectToRegister(View view) {
        Intent myIntent = new Intent(this, RegisterActivity.class);
        startActivity(myIntent);
    }
}