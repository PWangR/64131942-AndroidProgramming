package dp.wang;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Transaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UploadQuestionsActivity extends AppCompatActivity {

    private static final String TAG = "UploadQuestions";

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_questions);

        db = FirebaseFirestore.getInstance();

        Button btnUpload = findViewById(R.id.btnUploadQuestions);
        btnUpload.setOnClickListener(v -> uploadQuestions());
    }

    private void uploadQuestions() {
        // Tạo danh sách câu hỏi mẫu (bạn có thể thay bằng data thực)
        List<Question> questionList = new ArrayList<>();
        questionList.add(new Question(
                "Java là ngôn ngữ?",
                new String[]{"Thông dịch", "Biên dịch", "Vừa biên dịch vừa thông dịch", "Không phải ngôn ngữ"},
                2));
        questionList.add(new Question(
                "Từ khóa khai báo lớp trong Java là gì?",
                new String[]{"define", "struct", "class", "object"},
                2));

        // Upload từng câu hỏi vào Firestore trong collection "questions"
        for (Question question : questionList) {
            db.collection("questions")
                    .add(question)
                    .addOnSuccessListener(documentReference -> {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        Toast.makeText(this, "Upload thành công câu hỏi: " + question.getQuestion(), Toast.LENGTH_SHORT).show();
                    })
                    .addOnFailureListener(e -> {
                        Log.w(TAG, "Error adding document", e);
                        Toast.makeText(this, "Upload lỗi: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        }
    }
}
