package dp.wang;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button javaButton = findViewById(R.id.javaButton);
        Button pythonButton = findViewById(R.id.pythonButton);
        Button cppButton = findViewById(R.id.cppButton);
        Button cButton = findViewById(R.id.cButton);
        Button uploadButton = findViewById(R.id.uploadButton);
        Button addQuestionButton = findViewById(R.id.addQuestionButton); // Nút mới

        javaButton.setOnClickListener(v -> startQuiz("Java"));
        pythonButton.setOnClickListener(v -> startQuiz("Python"));
        cppButton.setOnClickListener(v -> startQuiz("C++"));
        cButton.setOnClickListener(v -> startQuiz("C"));
        uploadButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, UploadQuestions.class)));
        addQuestionButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, AddQuestionActivity.class)));
    }

    private void startQuiz(String topic) {
        Intent intent = new Intent(MainActivity.this, QuizActivity.class);
        intent.putExtra("TOPIC", topic);
        startActivity(intent);
    }
}