package dp.wang;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity {

    TextView txtResult;
    Button btnRetry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        txtResult = findViewById(R.id.txtResult);
        btnRetry = findViewById(R.id.btnRetry);

        int score = getIntent().getIntExtra("score", 0);
        int total = getIntent().getIntExtra("total", 0);
        String topic = getIntent().getStringExtra("topic");
        // lấy chủ đề

        // Lưu điểm với chủ đề
        ScoreStorage.saveScore(this, topic, score);

        txtResult.setText("Điểm của bạn: " + score + " / " + total + "\nChủ đề: " + topic);

        QuizProgressManager progressManager = new QuizProgressManager(this);

        // Ghi nhận nếu đạt điểm tuyệt đối
        progressManager.addStarIfPerfectScore(score);

        btnRetry.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, QuizActivity.class);
            intent.putExtra("topic", topic); // nếu cần truyền lại topic khi chơi lại
            startActivity(intent);
            finish();
        });

        Button btnHome = findViewById(R.id.btnHome);
        btnHome.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });

        Button btnHistory = findViewById(R.id.btnHistory);
        btnHistory.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, HistoryActivity.class);
            startActivity(intent);
        });


    }
}

