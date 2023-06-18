package com.example.testingsystemproject;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.testingsystemproject.dao.binding_types.QuestionWithAnswer;
import com.example.testingsystemproject.dtos.TestResult;
import com.example.testingsystemproject.repositories.QuestionRepository;
import com.example.testingsystemproject.repositories.TestRepository;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

enum QuizState {
    Question,
    Solution
}

@AndroidEntryPoint
public class QuizActivity extends AppCompatActivity {
    public static final String EXTRA_SCORE = "extraScore";
    private static final long ONE_ANSWER_COUNT_DOWN_TIME_IN_MILLISECONDS = 30000;

    private TextView textViewQuestion;
    private TextView textViewScore;
    private TextView textViewQuestionCount;
    private TextView textViewCountDown;
    private RadioGroup rbGroup;
    private final ArrayList<RadioButton> radioButtons = new ArrayList<>(3);
    private Button buttonConfirmNext;

    private ColorStateList textColorDefaultRadioButtons;
    private ColorStateList textColorDefaultCoolDown;

    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;

    private long categoryId = -1;

    private final ArrayList<QuestionWithAnswer> questionList = new ArrayList<>();

    List<Long> answersList = new ArrayList<>();

    List <Boolean> rightAnswersList = new ArrayList<>();

    private int currentQuestionIndex = 0;
    private static final int initial_requested_question_count = 5;
    public final int additional_requested_question_count = 10;
    public int score;
    private long backPressedTime;
    private QuizState state = QuizState.Question;


    @Inject
    public QuestionRepository questionRepository;

    @Inject
    public TestRepository testRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);
        bindViews();

        textColorDefaultRadioButtons = radioButtons.get(0).getTextColors();
        textColorDefaultCoolDown = textViewCountDown.getTextColors();
        categoryId = getIntent().getLongExtra("categoryId", -1);
        getNewQuestions(initial_requested_question_count);
        showNextQuestion();
        buttonConfirmNext.setOnClickListener(this::onNextButton);
    }

    private void onNextButton(View v) {
        boolean anyButtonIsChecked = radioButtons.stream().anyMatch(CompoundButton::isChecked);
        switch (state) {
            case Question:
                if (!anyButtonIsChecked) {
                    Snackbar.make(v, R.string.enforce_answer, BaseTransientBottomBar.LENGTH_SHORT).show();
                    return;
                }
                checkAnswer();
                state = QuizState.Solution;
                showSolution();
                break;
            case Solution:
                state = QuizState.Question;
                showNextQuestion();
                break;
        }
    }

    private void getNewQuestions(int n) {
        List<QuestionWithAnswer> fromRepo = questionRepository.getByCategoryId(categoryId, n, questionList.stream().map(x -> x.question.questionId).collect(Collectors.toList()));
        Collections.shuffle(fromRepo);
        questionList.addAll(fromRepo);
    }

    private void bindViews() {
        textViewQuestion = findViewById(R.id.text_view_question);
        textViewScore = findViewById(R.id.text_view_score);
        textViewQuestionCount = findViewById(R.id.text_view_question_count);
        textViewCountDown = findViewById(R.id.text_view_countdown);
        rbGroup = findViewById(R.id.radio_group);
        radioButtons.add((RadioButton) findViewById(R.id.radio_button1));
        radioButtons.add((RadioButton) findViewById(R.id.radio_button2));
        radioButtons.add((RadioButton) findViewById(R.id.radio_button3));
        buttonConfirmNext = findViewById(R.id.button_confirm_next);
    }

    private void showNextQuestion() {
        resetView();
        if (currentQuestionIndex >= questionList.size())
        {
            finishQuiz();
            return;
        }
        QuestionWithAnswer currentQuestion = questionList.get(currentQuestionIndex);
        textViewQuestion.setText(currentQuestion.question.question);
        for (int i = 0; i < 3; i++)
            radioButtons.get(i).setText(currentQuestion.answers.get(i).answer);
        textViewQuestionCount.setText("Question: " + (currentQuestionIndex + 1) + "/" + questionList.size());
        buttonConfirmNext.setText("Confirm");
        timeLeftInMillis = ONE_ANSWER_COUNT_DOWN_TIME_IN_MILLISECONDS;
        startCountDown();
        currentQuestionIndex++;
    }

    private void resetView() {
        setDefaultColorOnRadioButtons();
        textViewCountDown.setTextColor(textColorDefaultCoolDown);
        rbGroup.clearCheck();
    }

    private void setDefaultColorOnRadioButtons() {
        for (RadioButton rb: radioButtons) rb.setTextColor(textColorDefaultRadioButtons);
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
        }
    }

    private void checkAnswer() {
        countDownTimer.cancel();

        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        int answerIndex = rbGroup.indexOfChild(rbSelected);
        QuestionWithAnswer currentQuestion = questionList.get(currentQuestionIndex - 1);
        long rightAnswerId = currentQuestion.question.rightAnswerId;
        long userAnswerId = currentQuestion.answers.get(answerIndex).answerId;

        rightAnswersList.add(userAnswerId==rightAnswerId);
        answersList.add(userAnswerId);

        if (userAnswerId == rightAnswerId) {
            score++;
            textViewScore.setText("Score: " + score);
            if (timeLeftInMillis > 15000 && questionList.size() < 15)
            {
                Snackbar.make(buttonConfirmNext.getRootView(), "Будут добавлены дополнительные вопросы", BaseTransientBottomBar.LENGTH_SHORT).show();
                getNewQuestions(additional_requested_question_count);
            }
            return;
        }
        if (questionList.size() < 15)
        {
            Snackbar.make(buttonConfirmNext.getRootView(), "Будут добавлены дополнительные вопросы", BaseTransientBottomBar.LENGTH_SHORT).show();
            getNewQuestions(additional_requested_question_count);
        }
    }

    private void showSolution() {
        for (RadioButton rb: radioButtons) rb.setTextColor(Color.RED);

        int rightAnswerIndex = -1;
        QuestionWithAnswer currentQuestion = questionList.get(currentQuestionIndex - 1);
        for (int i = 0; i < currentQuestion.answers.size(); i++){
            if (currentQuestion.answers.get(i).answerId == currentQuestion.question.rightAnswerId){
                rightAnswerIndex=i;
                break;
            }
        }
        radioButtons.get(rightAnswerIndex).setTextColor(Color.GREEN);
        textViewQuestion.setText("        Answer" + (rightAnswerIndex + 1) + " is correct");
        buttonConfirmNext.setText(currentQuestionIndex < questionList.size() ? "Next" : "Finish");
    }

    private void finishQuiz() {
        TestResult testResult = new TestResult(
                categoryId,
                MyApplication.instance.user.userId,
                answersList,
                questionList.stream().map(x -> x.question.questionId).collect(Collectors.toList()),
                rightAnswersList,
                score
        );
        testRepository.saveTestResult(testResult);
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_SCORE, score);
        setResult(RESULT_OK, resultIntent);
        showToast(textViewScore);
        finish();
    }

    public void showToast(View view){
        Toast toast = Toast.makeText(this, "Your score:" + score, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP, 100,460);
        toast.show();
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