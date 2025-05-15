package dp.wang;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btnStartQuiz, btnChooseTopic;
    TextView txtTotalStars;
    QuizProgressManager progressManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStartQuiz = findViewById(R.id.btnStartQuiz);
        btnChooseTopic = findViewById(R.id.btnChooseTopic);

        txtTotalStars = findViewById(R.id.txtTotalStars); // gắn đúng id
        progressManager = new QuizProgressManager(this);

        int stars = progressManager.getTotalStars();
        txtTotalStars.setText("🌟 Sao đạt được: " + stars);

        Button btnHistory = findViewById(R.id.btnHistory);
        btnHistory.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
            startActivity(intent);
        });



        btnStartQuiz.setOnClickListener(v -> {
            Intent intent = new Intent(this, QuizActivity.class);
            intent.putExtra("topic", "all"); // hoặc mặc định Java
            startActivity(intent);
        });

        btnChooseTopic.setOnClickListener(v -> {
            startActivity(new Intent(this, ChooseTopicActivity.class));
        });

    }
}

