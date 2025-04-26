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

        String topic = getIntent().getStringExtra("TOPIC");
        questions = getQuestionsForTopic(topic);

        if (questions.isEmpty()) {
            Toast.makeText(this, "Không có câu hỏi cho chủ đề này!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        displayQuestion();

        option1Button.setOnClickListener(v -> checkAnswer(0));
        option2Button.setOnClickListener(v -> checkAnswer(1));
        option3Button.setOnClickListener(v -> checkAnswer(2));
        option4Button.setOnClickListener(v -> checkAnswer(3));
    }

    private void displayQuestion() {
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

    private List<Question> getQuestionsForTopic(String topic) {
        List<Question> questions = new ArrayList<>();
        switch (topic) {
            case "Java":
                questions.add(new Question("Kiểu dữ liệu nào dùng để lưu số nguyên trong Java?",
                        new String[]{"int", "float", "String", "boolean"}, 0));
                questions.add(new Question("Phương thức nào dùng để in ra màn hình trong Java?",
                        new String[]{"print()", "System.out.println()", "write()", "display()"}, 1));
                break;
            case "Python":
                questions.add(new Question("Cú pháp nào dùng để khai báo hàm trong Python?",
                        new String[]{"function", "def", "fun", "proc"}, 1));
                questions.add(new Question("Thư viện nào dùng để làm việc với mảng trong Python?",
                        new String[]{"pandas", "numpy", "matplotlib", "requests"}, 1));
                break;
            case "C++":
                questions.add(new Question("Ký hiệu nào dùng để truy cập thành viên lớp trong C++?",
                        new String[]{".", "->", "::", "&"}, 0));
                questions.add(new Question("Hàm nào là điểm bắt đầu của chương trình C++?",
                        new String[]{"start()", "main()", "begin()", "init()"}, 1));
                break;
        }
        return questions;
    }
}