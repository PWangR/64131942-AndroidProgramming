package dp.wang;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddQuestionActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private Spinner topicSpinner;
    private EditText questionTextEditText, option1EditText, option2EditText, option3EditText, option4EditText, correctAnswerEditText;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        dbHelper = new DatabaseHelper(this);

        // Khởi tạo các view
        topicSpinner = findViewById(R.id.topicSpinner);
        questionTextEditText = findViewById(R.id.questionTextEditText);
        option1EditText = findViewById(R.id.option1EditText);
        option2EditText = findViewById(R.id.option2EditText);
        option3EditText = findViewById(R.id.option3EditText);
        option4EditText = findViewById(R.id.option4EditText);
        correctAnswerEditText = findViewById(R.id.correctAnswerEditText);
        saveButton = findViewById(R.id.saveButton);

        // Thiết lập spinner cho các chủ đề
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.topics_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        topicSpinner.setAdapter(adapter);

        // Xử lý sự kiện nút Lưu
        saveButton.setOnClickListener(v -> saveQuestion());
    }

    private void saveQuestion() {
        try {
            // Lấy dữ liệu từ các trường nhập liệu
            String topic = topicSpinner.getSelectedItem().toString();
            String questionText = questionTextEditText.getText().toString().trim();
            String option1 = option1EditText.getText().toString().trim();
            String option2 = option2EditText.getText().toString().trim();
            String option3 = option3EditText.getText().toString().trim();
            String option4 = option4EditText.getText().toString().trim();
            String correctAnswerStr = correctAnswerEditText.getText().toString().trim();

            // Kiểm tra dữ liệu đầu vào
            if (questionText.isEmpty() || option1.isEmpty() || option2.isEmpty() || option3.isEmpty() || option4.isEmpty() || correctAnswerStr.isEmpty()) {
                Toast.makeText(this, "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                return;
            }

            int correctAnswerIndex;
            try {
                correctAnswerIndex = Integer.parseInt(correctAnswerStr);
                if (correctAnswerIndex < 0 || correctAnswerIndex > 3) {
                    Toast.makeText(this, "Chỉ số đáp án đúng phải từ 0 đến 3!", Toast.LENGTH_SHORT).show();
                    return;
                }
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Chỉ số đáp án đúng phải là số!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Tạo đối tượng Question
            String[] options = {option1, option2, option3, option4};
            Question question = new Question(questionText, options, correctAnswerIndex);

            // Lưu vào SQLite
            dbHelper.addQuestion(question, topic);
            dbHelper.logAllQuestions(); // In dữ liệu để kiểm tra

            Toast.makeText(this, "Đã lưu câu hỏi thành công!", Toast.LENGTH_SHORT).show();
            finish();
        } catch (Exception e) {
            Toast.makeText(this, "Lỗi khi lưu câu hỏi: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}