package dp.wang;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.Collections;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    TextView txtQuestion;
    Button btnA, btnB, btnC, btnD;

    List<Question> questionList;
    int currentIndex = 0;
    int score = 0;

    String topic;

    Question currentQuestion;

    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // ✅ Nhận chủ đề từ Intent
        topic = getIntent().getStringExtra("topic");
        if (topic == null) {
            topic = "default_topic";  // hoặc bạn xử lý nếu null
        }

        questionList = QuestionBank.getQuestionsByTopic(topic);
        Collections.shuffle(questionList);

        progressBar = findViewById(R.id.progressBar);


        txtQuestion = findViewById(R.id.txtQuestion);
        btnA = findViewById(R.id.btnA);
        btnB = findViewById(R.id.btnB);
        btnC = findViewById(R.id.btnC);
        btnD = findViewById(R.id.btnD);




        int questionCount = Math.min(10, questionList.size());
        questionList = questionList.subList(0, questionCount);

        loadNextQuestion();

        btnA.setOnClickListener(v -> checkAnswer(0));
        btnB.setOnClickListener(v -> checkAnswer(1));
        btnC.setOnClickListener(v -> checkAnswer(2));
        btnD.setOnClickListener(v -> checkAnswer(3));
    }


    private void loadNextQuestion() {
        if (currentIndex < questionList.size()) {
            currentQuestion = questionList.get(currentIndex);
            txtQuestion.setText(currentQuestion.getQuestion());

            String[] options = currentQuestion.getOptions();
            btnA.setText(options[0]);
            btnB.setText(options[1]);
            btnC.setText(options[2]);
            btnD.setText(options[3]);

            // Cập nhật progress
            int progressPercent = (int) (((currentIndex + 1) * 100.0f) / questionList.size());
            progressBar.setProgress(progressPercent);
        } else {
            showResult();
        }
    }


    private void checkAnswer(int selectedIndex) {
        if (selectedIndex == currentQuestion.getCorrectAnswerIndex()) {
            score++;
        }

        currentIndex++;
        loadNextQuestion();
    }

    private void showResult() {
        Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
        intent.putExtra("score", score);
        intent.putExtra("total", questionList.size());
        intent.putExtra("topic", topic);  // truyền thêm chủ đề
        startActivity(intent);
        finish();
    }

}

