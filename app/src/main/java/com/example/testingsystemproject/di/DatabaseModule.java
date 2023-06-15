package com.example.testingsystemproject.di;

import android.content.Context;

import androidx.room.Room;

import com.example.testingsystemproject.MyApplication;
import com.example.testingsystemproject.database.AppDatabase;
import com.example.testingsystemproject.models.Answer;
import com.example.testingsystemproject.models.Category;
import com.example.testingsystemproject.models.Question;
import com.example.testingsystemproject.models.SecurityQuestion;
import com.example.testingsystemproject.repositories.CategoryRepository;
import com.example.testingsystemproject.repositories.QuestionRepository;
import com.example.testingsystemproject.repositories.SecurityQuestionRepository;
import com.example.testingsystemproject.repositories.TestQuestionRepository;
import com.example.testingsystemproject.repositories.TestRepository;
import com.example.testingsystemproject.repositories.UserAnswerRepository;
import com.example.testingsystemproject.repositories.UserRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.qualifiers.ApplicationContext;

@Module
@InstallIn(ActivityComponent.class)
public class DatabaseModule {
    @Provides
    public static AppDatabase provideAppDatabase(@ApplicationContext Context context) {
        AppDatabase db = Room
                .databaseBuilder(context, AppDatabase.class, "testing_system")
                .allowMainThreadQueries()
                .build();
        if (!db.securityQuestionDao().any()) prepopulate(db);
        return db;
    }

    private static void prepopulate(AppDatabase db) {
        db.runInTransaction(() -> {
            db.securityQuestionDao().insert(
                    new SecurityQuestion("Option 1"),
                    new SecurityQuestion("Option 2"),
                    new SecurityQuestion("Option 3")
            );
            db.categoryDao().insert(
                    new Category("Животные"),
                    new Category("Люди"),
                    new Category("Случайные"),
                    new Category("Музыка"),
                    new Category("Спорт")
            );
            try {
                prepopulateQuestions(db);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private static void prepopulateQuestions(AppDatabase db) throws IOException {
        for (String fileName : MyApplication.instance.getAssets().list("questionsByCategory")) {
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(MyApplication.instance.getAssets().open("questionsByCategory/" + fileName), StandardCharsets.UTF_8))
            ) {
                String mLine;
                while ((mLine = reader.readLine()) != null) {
                    String[] parts = mLine.substring(0, mLine.length()).split("/");
                    int index = Integer.parseInt(parts[2]);
                    String[] answers = parts[1].split(",");
                    int categoryId = Integer.parseInt(fileName.split("\\.")[0]);
                    Question question = new Question(parts[0], categoryId, null);
                    long questionId = db.questionDao().insertQuestion(question);
                    List<Answer> answerList = Arrays.stream(answers).map(x -> new Answer(x.trim(), questionId, categoryId)).collect(Collectors.toList());
                    long[] answerIds = db.questionDao().insertAnswers(answerList);
                    //TODO: set actual right answer
                    db.questionDao().setRightAnswer(questionId, answerIds[index]);
                }
            }
        }
    }

    @Provides
    public static UserRepository provideUserRepository(AppDatabase appDatabase) {
        return new UserRepository(appDatabase);
    }

    @Provides
    public static QuestionRepository provideQuestionRepository(AppDatabase appDatabase) {
        return new QuestionRepository(appDatabase);
    }

    @Provides
    public static CategoryRepository provideCategoryRepository(AppDatabase appDatabase) {
        return new CategoryRepository(appDatabase);
    }

    @Provides
    public static SecurityQuestionRepository provideSecurityQuestionRepository(AppDatabase appDatabase) {
        return new SecurityQuestionRepository(appDatabase);
    }

    @Provides
    public static UserAnswerRepository provideUserAnswerRepository(AppDatabase appDatabase) {
        return new UserAnswerRepository(appDatabase);
    }

    @Provides
    public static TestRepository provideTestRepository(AppDatabase appDatabase) {
        return new TestRepository(appDatabase);
    }

    @Provides
    public static TestQuestionRepository provideTestQuestionRepository(AppDatabase appDatabase) {
        return new TestQuestionRepository(appDatabase);
    }
}