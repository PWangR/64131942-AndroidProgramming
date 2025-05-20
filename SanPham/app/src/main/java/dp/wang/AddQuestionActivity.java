package dp.wang;

import android.os.Bundle;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.List;

public class AddQuestionActivity extends AppCompatActivity {

    private EditText edtTopic, edtQuestion, edtOption1, edtOption2, edtOption3, edtOption4;
    private RadioGroup radioGroup;
    private Button btnSave;

    private DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        edtTopic = findViewById(R.id.edtTopic);
        edtQuestion = findViewById(R.id.edtQuestion);
        edtOption1 = findViewById(R.id.edtOption1);
        edtOption2 = findViewById(R.id.edtOption2);
        edtOption3 = findViewById(R.id.edtOption3);
        edtOption4 = findViewById(R.id.edtOption4);
        radioGroup = findViewById(R.id.radioGroup);
        btnSave = findViewById(R.id.btnSaveQuestion);

        dbRef = FirebaseDatabase.getInstance().getReference("Questions");


        btnSave.setOnClickListener(v -> saveQuestion());
    }

    private void saveQuestion() {
        String topic = edtTopic.getText().toString().trim();
        String questionText = edtQuestion.getText().toString().trim();
        String opt1 = edtOption1.getText().toString().trim();
        String opt2 = edtOption2.getText().toString().trim();
        String opt3 = edtOption3.getText().toString().trim();
        String opt4 = edtOption4.getText().toString().trim();
        int selectedId = radioGroup.getCheckedRadioButtonId();

        if (topic.isEmpty() || questionText.isEmpty() || opt1.isEmpty() || opt2.isEmpty()
                || opt3.isEmpty() || opt4.isEmpty() || selectedId == -1) {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        List<String> options = Arrays.asList(opt1, opt2, opt3, opt4);
        int answerIndex = radioGroup.indexOfChild(findViewById(selectedId));

        Question question = new Question(questionText, options, answerIndex);

        // 🔁 Ghi đúng nhánh: Questions/<tên chủ đề>
        dbRef.child(topic).push().setValue(question).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(this, "Đã thêm câu hỏi!", Toast.LENGTH_SHORT).show();
                edtQuestion.setText("");
                edtOption1.setText("");
                edtOption2.setText("");
                edtOption3.setText("");
                edtOption4.setText("");
                radioGroup.clearCheck();
            } else {
                Toast.makeText(this, "Lỗi khi thêm câu hỏi", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
