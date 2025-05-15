package dp.wang;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    private TextView questionTextView, scoreTextView;
    private Button option1Button, option2Button, option3Button, option4Button;
    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionTextView = findViewById(R.id.questionTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        option1Button = findViewById(R.id.option1Button);
        option2Button = findViewById(R.id.option2Button);
        option3Button = findViewById(R.id.option3Button);
        option4Button = findViewById(R.id.option4Button);

        dbHelper = new DatabaseHelper(this);
        String topic = getIntent().getStringExtra("TOPIC");
        questions = new ArrayList<>();

        getQuestionsForTopic(topic);

        option1Button.setOnClickListener(v -> checkAnswer(0));
        option2Button.setOnClickListener(v -> checkAnswer(1));
        option3Button.setOnClickListener(v -> checkAnswer(2));
        option4Button.setOnClickListener(v -> checkAnswer(3));
    }

    private void displayQuestion() {
        if (questions.isEmpty()) {
            Toast.makeText(this, "Không có câu hỏi cho chủ đề này!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        Question currentQuestion = questions.get(currentQuestionIndex);
        questionTextView.setText(currentQuestion.getQuestionText());
        String[] options = currentQuestion.getOptions();
        option1Button.setText(options[0]);
        option2Button.setText(options[1]);
        option3Button.setText(options[2]);
        option4Button.setText(options[3]);
        scoreTextView.setText("Điểm: " + score);
    }

    private void checkAnswer(int selectedIndex) {
        Question currentQuestion = questions.get(currentQuestionIndex);
        if (selectedIndex == currentQuestion.getCorrectAnswerIndex()) {
            score += 10;
            Toast.makeText(this, "Đúng!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Sai!", Toast.LENGTH_SHORT).show();
        }

        currentQuestionIndex++;
        if (currentQuestionIndex < questions.size()) {
            displayQuestion();
        } else {
            Toast.makeText(this, "Hoàn thành! Điểm cuối: " + score, Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private void getQuestionsForTopic(String topic) {
        questions = dbHelper.getQuestionsForTopic(topic);
        if (questions.isEmpty()) {
            Toast.makeText(this, "Không có câu hỏi cho chủ đề này!", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            displayQuestion();
        }
    }
}