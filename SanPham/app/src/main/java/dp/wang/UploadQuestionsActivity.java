package dp.wang;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.*;
import java.util.*;

public class UploadQuestionsActivity extends AppCompatActivity {

    private static final String TAG = "FirebaseQuiz";
    private Spinner spinnerTopic;
    private DatabaseReference ref;
    private String selectedTopic = "Java"; // Mặc định

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_questions);

        spinnerTopic = findViewById(R.id.spinnerTopic);
        Button btnDownload = findViewById(R.id.btnDownloadAndStartQuiz);
        Button btnUpload = findViewById(R.id.btnUploadQuestions);
        btnUpload.setOnClickListener(v -> {
            Intent intent = new Intent(UploadQuestionsActivity.this, AddQuestionActivity.class);
            startActivity(intent);
            finish();
        });

        // Cài đặt sự kiện chọn chủ đề
        spinnerTopic.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, android.view.View view, int position, long id) {
                selectedTopic = adapterView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                selectedTopic = "Java";
            }
        });

        // Thêm dòng này để set ref mặc định lúc khởi tạo (tránh null nếu chưa chọn gì)
        ref = FirebaseDatabase.getInstance().getReference("Questions").child(selectedTopic);

        btnDownload.setOnClickListener(v -> downloadQuestionsAndStartQuiz());
    }

    private void downloadQuestionsAndStartQuiz() {
        // Lấy chủ đề chính xác từ Spinner trước khi tải dữ liệu
        selectedTopic = spinnerTopic.getSelectedItem() != null ? spinnerTopic.getSelectedItem().toString() : "Java";

        // Cập nhật ref tương ứng
        ref = FirebaseDatabase.getInstance().getReference("Questions").child(selectedTopic);

        Log.d(TAG, "Tải câu hỏi cho topic: " + selectedTopic);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Question> downloadedQuestions = new ArrayList<>();

                for (DataSnapshot questionSnapshot : snapshot.getChildren()) {
                    Question question = questionSnapshot.getValue(Question.class);
                    if (question != null) {
                        downloadedQuestions.add(question);
                    } else {
                        Log.e(TAG, "Không thể ánh xạ một câu hỏi");
                    }
                }

                // Lưu vào QuestionBank
                QuestionBank.setQuestionsFromFirebase(selectedTopic, downloadedQuestions);
                Log.d(TAG, "Lưu vào QuestionBank: " + downloadedQuestions.size() + " câu hỏi");

                Toast.makeText(UploadQuestionsActivity.this, "Đã tải " + downloadedQuestions.size() + " câu hỏi.", Toast.LENGTH_SHORT).show();

                // Chuyển sang QuizActivity
                Intent intent = new Intent(UploadQuestionsActivity.this, QuizActivity.class);
                intent.putExtra("topic", selectedTopic);
                startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UploadQuestionsActivity.this, "Lỗi tải dữ liệu", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "Lỗi tải dữ liệu: " + error.getMessage());
            }
        });
    }
}
