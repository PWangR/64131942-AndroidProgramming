package dp.wang;

import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddQuestionActivity extends AppCompatActivity {

    private Spinner spinnerTopic;
    private EditText edtNewTopic, edtQuestion, edtOption1, edtOption2, edtOption3, edtOption4;
    private RadioGroup radioGroup;
    private Button btnSave;

    private DatabaseReference dbRef;

    private ArrayAdapter<String> adapter;
    private List<String> topics = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        spinnerTopic = findViewById(R.id.spinnerTopic);
        edtNewTopic = findViewById(R.id.edtNewTopic);
        edtQuestion = findViewById(R.id.edtQuestion);
        edtOption1 = findViewById(R.id.edtOption1);
        edtOption2 = findViewById(R.id.edtOption2);
        edtOption3 = findViewById(R.id.edtOption3);
        edtOption4 = findViewById(R.id.edtOption4);
        radioGroup = findViewById(R.id.radioGroup);
        btnSave = findViewById(R.id.btnSaveQuestion);

        dbRef = FirebaseDatabase.getInstance().getReference("Questions");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, topics);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTopic.setAdapter(adapter);

        loadTopicsFromFirebase();

        spinnerTopic.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = (String) parent.getItemAtPosition(position);
                if ("Chủ đề khác".equals(selected)) {
                    edtNewTopic.setVisibility(View.VISIBLE);
                } else {
                    edtNewTopic.setVisibility(View.GONE);
                    edtNewTopic.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                edtNewTopic.setVisibility(View.GONE);
            }
        });

        btnSave.setOnClickListener(v -> saveQuestion());
    }

    private void loadTopicsFromFirebase() {
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                topics.clear();
                for (DataSnapshot topicSnapshot : snapshot.getChildren()) {
                    String topicName = topicSnapshot.getKey();
                    if (topicName != null) {
                        topics.add(topicName);
                    }
                }
                topics.add("Chủ đề khác");
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AddQuestionActivity.this, "Lỗi tải chủ đề", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveQuestion() {
        String topic = spinnerTopic.getSelectedItem().toString();
        if ("Chủ đề khác".equals(topic)) {
            topic = edtNewTopic.getText().toString().trim();
            if (topic.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập chủ đề mới", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        String questionText = edtQuestion.getText().toString().trim();
        String opt1 = edtOption1.getText().toString().trim();
        String opt2 = edtOption2.getText().toString().trim();
        String opt3 = edtOption3.getText().toString().trim();
        String opt4 = edtOption4.getText().toString().trim();
        int selectedId = radioGroup.getCheckedRadioButtonId();

        if (questionText.isEmpty() || opt1.isEmpty() || opt2.isEmpty()
                || opt3.isEmpty() || opt4.isEmpty() || selectedId == -1) {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        List<String> options = Arrays.asList(opt1, opt2, opt3, opt4);
        int answerIndex = radioGroup.indexOfChild(findViewById(selectedId));

        Question question = new Question(questionText, options, answerIndex);

        dbRef.child(topic).push().setValue(question).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(this, "Đã thêm câu hỏi!", Toast.LENGTH_SHORT).show();
                edtQuestion.setText("");
                edtOption1.setText("");
                edtOption2.setText("");
                edtOption3.setText("");
                edtOption4.setText("");
                radioGroup.clearCheck();
                edtNewTopic.setText("");
                edtNewTopic.setVisibility(View.GONE);
                spinnerTopic.setSelection(0);
            } else {
                Toast.makeText(this, "Lỗi khi thêm câu hỏi", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
