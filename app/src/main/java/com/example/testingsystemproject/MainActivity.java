package com.example.testingsystemproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.testingsystemproject.R;
import com.example.testingsystemproject.OptionActivity;
import com.example.testingsystemproject.repositories.UserRepository;
import com.google.android.material.textfield.TextInputLayout;
import javax.inject.Inject;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    @Inject
    UserRepository repository;
    TextInputLayout Login, Password;
    Button LoginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Login = findViewById(R.id.textInputEmail);
        Password = findViewById(R.id.textInputPassword);
        LoginBtn = findViewById(R.id.idBtnLogin);
        LoginBtn.setOnClickListener(view -> login());
    }

    private void login() {
        String userName = Login.getEditText().getText().toString();
        String password = Password.getEditText().getText().toString();
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) return;
        boolean authenticated = repository.authenticate(userName, password);
        if (authenticated) {
            MyApplication app = (MyApplication) this.getApplication();
            app.authenticated = true;
            //TODO: redirect to account
        }
        EmptyEditTextAfterDataInsert();
    }

    public void EmptyEditTextAfterDataInsert(){
        Login.getEditText().getText().clear();
        Password.getEditText().getText().clear();
    }

    public void RedirectToRegister(View view) {
        Intent myIntent = new Intent(this, RegisterActivity.class);
        startActivity(myIntent);
    }
    public void RedirectToStartActivity(View view) {
        Intent myIntent = new Intent(this, StartActivity.class);
        startActivity(myIntent);
    }
}