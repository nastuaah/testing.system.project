package com.example.testingsystemproject;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.example.testingsystemproject.dtos.UserTestResultResponse;
import com.example.testingsystemproject.repositories.TestRepository;
import com.example.testingsystemproject.repositories.UserAnswerRepository;

import java.util.List;
import javax.inject.Inject;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class TestResActivity extends AppCompatActivity {
    @Inject
    TestRepository testRepository;
    @Inject
    UserAnswerRepository userAnswerRepository;
    private TextView previous = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_result);
        if (testRepository == null) return;
        @SuppressLint("WrongViewCast")
        FrameLayout textViewBlock = findViewById(R.id.TestResults);
        List<UserTestResultResponse> userTestResultResponses = testRepository.getUserTestResults(MyApplication.instance.user.userId);
        for (UserTestResultResponse userTestResultResponse: userTestResultResponses) {
            TextView textView = new TextView(this);
            textView.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#00A4FE")));
            textView.setText((CharSequence) userTestResultResponse.questionAnswerResults);
            textView.setId(Button.generateViewId());
            ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.startToStart = ConstraintSet.PARENT_ID;
            layoutParams.endToEnd = ConstraintSet.PARENT_ID;
            if (previous == null) {
                layoutParams.topToTop = ConstraintSet.PARENT_ID;
                layoutParams.topMargin = 70;
            }
            else layoutParams.topToBottom = previous.getId();
            textView.setLayoutParams(layoutParams);
            textViewBlock.addView(textView);
            if (previous != null) {
                ConstraintLayout.LayoutParams previousLayoutParams = (ConstraintLayout.LayoutParams) previous.getLayoutParams();
                previousLayoutParams.bottomToTop = textView.getId();
            }
            previous = textView;
        }
    }
}
