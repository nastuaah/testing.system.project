package com.example.testingsystemproject.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.Date;

@Entity
public class User {
    public User(String userName, String passwordHash, String firstName, String middleName,
                String lastName, String email, String mobileNumber, Date dateOfBirth, String securityQuestion, String securityQuestionAnswer) {
        this.userName = userName;
        this.passwordHash = passwordHash;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.dateOfBirth = dateOfBirth;
        this.securityQuestion = securityQuestion;
        this.securityQuestionAnswer = securityQuestionAnswer;
    }

    @PrimaryKey(autoGenerate = true)
    public int userId;

    @ColumnInfo(name = "userName")
    public String userName;

    @ColumnInfo(name = "passwordHash")
    public String passwordHash;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "middle_name")
    public String middleName;

    @ColumnInfo(name = "last_name")
    public String lastName;

    @ColumnInfo(name = "email")
    public String email;

    @ColumnInfo(name = "mobile_number")
    public String mobileNumber;

    @ColumnInfo(name = "date_of_birth")
    public Date dateOfBirth;

    @ColumnInfo(name = "security_question")
    public String securityQuestion;

    @ColumnInfo(name = "security_question_answer")
    public String securityQuestionAnswer;
}
