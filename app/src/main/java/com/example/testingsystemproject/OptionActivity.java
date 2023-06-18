package com.example.testingsystemproject;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import com.example.testingsystemproject.R;
import com.example.testingsystemproject.models.Category;
import com.example.testingsystemproject.repositories.CategoryRepository;
import com.google.android.material.button.MaterialButton;
import java.util.List;
import javax.inject.Inject;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class OptionActivity extends AppCompatActivity {
    @Inject
    CategoryRepository categoryRepository;
    private MaterialButton previous = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        if (categoryRepository == null) return;
        ConstraintLayout btnBlock = findViewById(R.id.selectCategoryButtonLayout);
        List<Category> categories = categoryRepository.getAll();
        for (Category category: categories) {
            MaterialButton btn = new MaterialButton(this);
            btn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#00A4FE")));
            btn.setText(category.getName());
            btn.setId(Button.generateViewId());
            btn.setWidth(800);
            btn.setHeight(170);
            btn.setTextSize(20);
            ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.startToStart = ConstraintSet.PARENT_ID;
            layoutParams.endToEnd = ConstraintSet.PARENT_ID;
            if (previous == null) {
                layoutParams.topToTop = ConstraintSet.PARENT_ID;
                layoutParams.topMargin = 70;
            }
            else layoutParams.topToBottom = previous.getId();
            btn.setLayoutParams(layoutParams);
            btn.setOnClickListener(v -> {
                Intent intent = new Intent(this, QuizActivity.class);
                intent.putExtra("categoryId", category.getCategoryId());
                startActivity(intent);
                finish();
            });
            btnBlock.addView(btn);
            if (previous != null) {
                ConstraintLayout.LayoutParams previousLayoutParams = (ConstraintLayout.LayoutParams) previous.getLayoutParams();
                previousLayoutParams.bottomToTop = btn.getId();
            }
            previous = btn;
        }
    }
}
