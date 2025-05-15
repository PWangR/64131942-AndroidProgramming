package dp.wang;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ChooseTopicActivity extends AppCompatActivity {

    CardView cardJava, cardPython, cardHTML;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_topic);

        cardJava = findViewById(R.id.cardJava);
        cardPython = findViewById(R.id.cardPython);
        cardHTML = findViewById(R.id.cardHTML);

        cardJava.setOnClickListener(v -> startQuizWithTopic("Java"));
        cardPython.setOnClickListener(v -> startQuizWithTopic("Python"));
        cardHTML.setOnClickListener(v -> startQuizWithTopic("HTML"));
    }

    private void startQuizWithTopic(String selectedTopic) {
        Intent intent = new Intent(ChooseTopicActivity.this, QuizActivity.class);
        intent.putExtra("topic", selectedTopic);  // selectedTopic là String tên chủ đề bạn chọn
        startActivity(intent);
        finish();
    }
}
