package com.example.testingsystemproject;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.testingsystemproject.models.QuestionWithAnswer;
import com.example.testingsystemproject.repositories.QuestionRepository;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class QuizActivity extends AppCompatActivity {
    public static final String EXTRA_SCORE = "extraScore";
    private static final long COUNTDOWN_IN_MILLIS = 30000;

    private TextView textViewQuestion;
    private TextView textViewScore;
    private TextView textViewQuestionCount;
    private TextView textViewCountDown;
    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private Button buttonConfirmNext;

    private ColorStateList textColorDefaultRb;
    private ColorStateList textColorDefaultCd;

    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;

    private List<QuestionWithAnswer> questionList;

    private List<QuestionWithAnswer> addQuestionList;

    private int questionCounter;

    private int addQuestionCounter;

    public final int questionCountTotal=5;

    public final int addQuestionCountTotal=10;
    private QuestionWithAnswer currentQuestion;

    private QuestionWithAnswer additionalQuestion;

    private int score;
    private boolean answered;

    private long backPressedTime;

    @Inject
    public QuestionRepository questionRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);

        textViewQuestion = findViewById(R.id.text_view_question);
        textViewScore = findViewById(R.id.text_view_score);
        textViewQuestionCount = findViewById(R.id.text_view_question_count);
        textViewCountDown = findViewById(R.id.text_view_countdown);
        rbGroup = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.radio_button1);
        rb2 = findViewById(R.id.radio_button2);
        rb3 = findViewById(R.id.radio_button3);
        buttonConfirmNext = findViewById(R.id.button_confirm_next);

        textColorDefaultRb = rb1.getTextColors();
        textColorDefaultCd = textViewCountDown.getTextColors();
        questionList = questionRepository.getByCategoryId(savedInstanceState.getInt("categoryId"), questionCountTotal);
        addQuestionList = questionRepository.getByCategoryId(savedInstanceState.getInt("categoryId"), addQuestionCounter);
        Collections.shuffle(questionList);
        Collections.shuffle(addQuestionList);
        showNextQuestion();

        buttonConfirmNext.setOnClickListener(v -> {
            if (!answered) {
                if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked()) checkAnswer();
                else
                    Snackbar.make(v, R.string.enforce_answer, BaseTransientBottomBar.LENGTH_SHORT).show();
                return;
            }
            showNextQuestion();
        });
    }

    private void showNextQuestion() {
        rb1.setTextColor(textColorDefaultRb);
        rb2.setTextColor(textColorDefaultRb);
        rb3.setTextColor(textColorDefaultRb);
        rbGroup.clearCheck();

        if (questionCounter < questionCountTotal) {
            currentQuestion = questionList.get(questionCounter);

            textViewQuestion.setText(currentQuestion.question.question);
            rb1.setText(currentQuestion.answers.get(0).answer);
            rb2.setText(currentQuestion.answers.get(1).answer);
            rb3.setText(currentQuestion.answers.get(2).answer);

            questionCounter++;
            textViewQuestionCount.setText("Question: " + questionCounter + "/" + questionCountTotal);
            answered = false;
            buttonConfirmNext.setText("Confirm");

            timeLeftInMillis = COUNTDOWN_IN_MILLIS;
            startCountDown();
            return;
        }
        finishQuiz();
    }

    private void startCountDown() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                updateCountDownText();
                checkAnswer();
            }
        }.start();
    }

    private void updateCountDownText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        textViewCountDown.setText(timeFormatted);

        if (timeLeftInMillis < 10000) {
            textViewCountDown.setTextColor(Color.RED);
        } else {
            textViewCountDown.setTextColor(textColorDefaultCd);
        }
    }

    private void checkAnswer() {
        answered = true;

        countDownTimer.cancel();

        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        int answerIndex = rbGroup.indexOfChild(rbSelected);

        if (currentQuestion.answers.get(answerIndex).answerId==currentQuestion.question.rightAnswerId) {
            score++;
            textViewScore.setText("Score: " + score);
        }
        showSolution();
    }

    private void showSolution() {
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);

        int rightAnswerIndex = -1;

        for (int i=0; i<currentQuestion.answers.size(); i++){
            if (currentQuestion.answers.get(i).answerId==currentQuestion.question.rightAnswerId){
                rightAnswerIndex=i;
                break;
            }
        }

        switch (rightAnswerIndex) {
            case 1:
                rb1.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 1 is correct");
                break;
            case 2:
                rb2.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 2 is correct");
                break;
            case 3:
                rb3.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 3 is correct");
                break;
        }

        if (questionCounter < questionCountTotal) {
            buttonConfirmNext.setText("Next");
        } else {
            buttonConfirmNext.setText("Finish");
        }
    }

    private void addQuestion() {

        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        int answerIndex = rbGroup.indexOfChild(rbSelected);

        if (timeLeftInMillis <= 20000 || currentQuestion.answers.get(answerIndex).answerId!=currentQuestion.question.rightAnswerId) {
            addQuestionCounter = +1;
            while (addQuestionCounter < addQuestionCountTotal) {
                additionalQuestion = addQuestionList.get(addQuestionCounter);
                textViewQuestion.setText(additionalQuestion.question.question);
                rb1.setText(additionalQuestion.answers.get(0).answer);
                rb2.setText(additionalQuestion.answers.get(1).answer);
                rb3.setText(additionalQuestion.answers.get(2).answer);
            }
            showSolution();
        }
    }


    private void finishQuiz() {
        addQuestion();
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_SCORE, score);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            finishQuiz();
        } else {
            Toast.makeText(this, "Press back again to finish", Toast.LENGTH_SHORT).show();
        }

        backPressedTime = System.currentTimeMillis();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
