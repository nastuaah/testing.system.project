package com.example.testingsystemproject;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.testing_system.R;
import com.example.testingsystemproject.helpers.EncryptionHelper;
import com.example.testingsystemproject.models.User;
import com.example.testingsystemproject.repositories.SecurityQuestionRepository;
import com.example.testingsystemproject.repositories.UserRepository;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Locale;
import javax.inject.Inject;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class RegisterActivity extends AppCompatActivity {
    @Inject
    UserRepository repository;
    @Inject
    SecurityQuestionRepository securityQuestionRepository;
    TextInputLayout Login, Password, Name, Surname, MiddleName, DateOfBirth, Email, MobileNumber, Question, Answer ;
    Button Register;
    String LoginHolder, PasswordHolder, NameHolder, SurnameHolder, MiddleNameHolder, DateOfBirthHolder, EmailHolder, MobileNumberHolder,  QuestionHolder, AnswerHolder;
    private TextInputEditText DateOfBirthEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Login = findViewById(R.id.idEditUserName);
        Password = findViewById(R.id.idEditPassword);
        Name = findViewById(R.id.idEditName);
        Surname = findViewById(R.id.idEditSurname);
        MiddleName = findViewById(R.id.idEditMiddleName);
        DateOfBirth = findViewById(R.id.idEditDateOfBirth);
        DateOfBirthEditText = findViewById(R.id.idEditTextDateOfBirth);
        DateOfBirthEditText.setOnClickListener(v -> {
            Calendar now = Calendar.getInstance();
            DatePickerDialog dialog = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
                now.set(Calendar.YEAR, year);
                now.set(Calendar.MONTH, month);
                now.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                DateOfBirthEditText.setText(dateFormatter.format(now.getTime()));
            }, now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
            dialog.show();
        });
        Email = findViewById(R.id.idEditEmail);
        MobileNumber = findViewById(R.id.idEditPhoneNumber);
        Question = findViewById(R.id.idEditSecurityQuestion);
        ((AutoCompleteTextView) Question.getEditText()).setAdapter(new ArrayAdapter<>(this, R.layout.list_item, securityQuestionRepository.getAllNames()));
        Answer = findViewById(R.id.idEditQuestionAnswer);
        Register = findViewById(R.id.idBtnRegister);
        Register.setOnClickListener(view -> {
            try {
                MoveToLocalStrings();
                if (!Validate()) {
                    Snackbar.make(view, R.string.validation_error, Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (repository.checkIfUserWithEmailExists(Email.getEditText().getText().toString())) {
                    Snackbar.make(view, R.string.email_already_exists, Snackbar.LENGTH_SHORT).show();
                    return;
                }
                User user = new User(LoginHolder, EncryptionHelper.toSHA256String(PasswordHolder),
                        NameHolder, MiddleNameHolder, SurnameHolder, EmailHolder, MobileNumberHolder,
                        dateFormatter.parse(DateOfBirthHolder), QuestionHolder, AnswerHolder);
                repository.insert(user);
                Snackbar.make(view, R.string.reg_success, Snackbar.LENGTH_SHORT).show();
                finish();
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void MoveToLocalStrings() {
        LoginHolder = Login.getEditText().getText().toString() ;
        PasswordHolder = Password.getEditText().getText().toString();
        NameHolder = Name.getEditText().getText().toString();
        SurnameHolder = Surname.getEditText().getText().toString();
        MiddleNameHolder = MiddleName.getEditText().getText().toString();
        DateOfBirthHolder = DateOfBirth.getEditText().getText().toString();
        EmailHolder = Email.getEditText().getText().toString();
        MobileNumberHolder = MobileNumber.getEditText().getText().toString();
        QuestionHolder = Question.getEditText().getText().toString();
        AnswerHolder = Answer.getEditText().getText().toString();
    }

    public boolean Validate(){
        return !TextUtils.isEmpty(LoginHolder) &&
                !TextUtils.isEmpty(PasswordHolder) &&
                !TextUtils.isEmpty(NameHolder) &&
                !TextUtils.isEmpty(SurnameHolder) &&
                !TextUtils.isEmpty(MiddleNameHolder) &&
                !TextUtils.isEmpty(DateOfBirthHolder) &&
                !TextUtils.isEmpty(EmailHolder) &&
                !TextUtils.isEmpty(MobileNumberHolder) &&
                !TextUtils.isEmpty(QuestionHolder) &&
                !TextUtils.isEmpty(AnswerHolder);
    }

    public void RedirectToLogin(View view) {
        Intent myIntent = new Intent(this, MainActivity.class);
        startActivity(myIntent);
    }
}