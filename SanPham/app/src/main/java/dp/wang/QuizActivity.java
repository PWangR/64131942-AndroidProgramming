package dp.wang;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    TextView txtQuestion;

    Button btnA, btnB, btnC, btnD;
    ProgressBar progressBar;

    List<Question> questionList;
    int currentIndex = 0;
    int score = 0;
    String topic;
    Question currentQuestion;

    ImageView imgMascot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Lấy topic từ Intent ngay đầu
        topic = getIntent().getStringExtra("topic");
        Log.d("Quiz", "Topic nhận được từ Intent: '" + topic + "'");
        if (topic == null) topic = "default_topic";

        // Lấy danh sách câu hỏi sau khi có topic
        questionList = QuestionBank.getQuestionsByTopic(topic);
        Log.d("Quiz", "Số câu hỏi nhận được: " + questionList.size());

        for (Question q : questionList) {
            Log.d("Quiz", "Q: " + q.getQuestion() + " | Options: " + q.getOptions().toString());
        }


        if (questionList == null || questionList.isEmpty()) {
            Toast.makeText(this, "Không có câu hỏi cho chủ đề này", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        // Phần còn lại giữ nguyên
        imgMascot = findViewById(R.id.imgMascot);

        Collections.shuffle(questionList);
        int questionCount = Math.min(10, questionList.size());
        questionList = questionList.subList(0, questionCount);

        txtQuestion = findViewById(R.id.txtQuestion);
        btnA = findViewById(R.id.btnA);
        btnB = findViewById(R.id.btnB);
        btnC = findViewById(R.id.btnC);
        btnD = findViewById(R.id.btnD);
        progressBar = findViewById(R.id.progressBar);

        btnA.setOnClickListener(v -> handleAnswer(0, btnA));
        btnB.setOnClickListener(v -> handleAnswer(1, btnB));
        btnC.setOnClickListener(v -> handleAnswer(2, btnC));
        btnD.setOnClickListener(v -> handleAnswer(3, btnD));

        loadNextQuestion();
    }


    private void loadNextQuestion() {
        if (currentIndex < questionList.size()) {
            currentQuestion = questionList.get(currentIndex);
            txtQuestion.setText(currentQuestion.getQuestion());

            List<String> options = currentQuestion.getOptions();
            btnA.setText(options.get(0));
            btnB.setText(options.get(1));
            btnC.setText(options.get(2));
            btnD.setText(options.get(3));


            enableAllButtons();
            resetButtonColors();

            imgMascot.setImageResource(R.drawable.mascot_default); // biểu cảm trung lập

            int progressPercent = (int) (((currentIndex + 1) * 100.0f) / questionList.size());
            progressBar.setProgress(progressPercent);
        } else {
            showResult();
        }
    }


    private void handleAnswer(int selectedIndex, Button selectedButton) {
        disableAllButtons();

        int correctIndex = currentQuestion.getAnswerIndex();
        if (selectedIndex == correctIndex) {
            score++;
            selectedButton.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
            imgMascot.setImageResource(R.drawable.mascot_happy); // ảnh vui
            imgMascot.startAnimation(AnimationUtils.loadAnimation(this, R.anim.mascot_jump));
        } else {
            selectedButton.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            getButtonByIndex(correctIndex).setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
            imgMascot.setImageResource(R.drawable.mascot_sad); // ảnh buồn
            imgMascot.startAnimation(AnimationUtils.loadAnimation(this, R.anim.mascot_shake));
        }

        new Handler().postDelayed(() -> {
            currentIndex++;
            loadNextQuestion();
        }, 1500);
    }


    private void disableAllButtons() {
        btnA.setEnabled(false);
        btnB.setEnabled(false);
        btnC.setEnabled(false);
        btnD.setEnabled(false);
    }

    private void enableAllButtons() {
        btnA.setEnabled(true);
        btnB.setEnabled(true);
        btnC.setEnabled(true);
        btnD.setEnabled(true);
    }

    private void resetButtonColors() {
        int defaultColor = getResources().getColor(R.color.orange); // hoặc màu mặc định khác
        btnA.setBackgroundTintList(ColorStateList.valueOf(defaultColor));
        btnB.setBackgroundTintList(ColorStateList.valueOf(defaultColor));
        btnC.setBackgroundTintList(ColorStateList.valueOf(defaultColor));
        btnD.setBackgroundTintList(ColorStateList.valueOf(defaultColor));
    }

    private Button getButtonByIndex(int index) {
        switch (index) {
            case 0: return btnA;
            case 1: return btnB;
            case 2: return btnC;
            case 3: return btnD;
            default: return null;
        }
    }

    private void showResult() {
        Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
        intent.putExtra("score", score);
        intent.putExtra("total", questionList.size());
        intent.putExtra("topic", topic);
        startActivity(intent);
        finish();
    }
}
