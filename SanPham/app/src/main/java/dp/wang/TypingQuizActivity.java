package dp.wang;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.*;

public class TypingQuizActivity extends AppCompatActivity {

    TextView txtQuestion;
    TextView txtQuestionIndex;
    EditText edtAnswer;
    Button btnSubmit;
    ImageView imgMascot;  // 👈 Thêm ImageView để hiển thị biểu cảm

    DatabaseReference typingQuizRef;

    int currentIndex = 0;
    int totalQuestions = 10;

    int score = 0;

    String correctAnswer = "";

    private static final String TAG = "TypingQuizActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_typing_quiz);

        txtQuestion = findViewById(R.id.txtQuestion);
        txtQuestionIndex = findViewById(R.id.txtQuestionIndex);
        edtAnswer = findViewById(R.id.edtAnswer);
        btnSubmit = findViewById(R.id.btnSubmit);
        imgMascot = findViewById(R.id.imgMascot); // 👈 Khởi tạo ImageView

        typingQuizRef = FirebaseDatabase.getInstance().getReference("Questions").child("TypingQuiz");
        Log.d(TAG, "Firebase path: Questions/TypingQuiz");

        loadQuestion(currentIndex);

        btnSubmit.setOnClickListener(view -> checkAnswer());
    }

    private void loadQuestion(int index) {
        edtAnswer.setText("");

        // 👇 Reset biểu cảm mascot về mặc định
        imgMascot.setImageResource(R.drawable.mascot_default);

        String questionKey = String.valueOf(index);
        Log.d(TAG, "Đang tải câu hỏi với key: " + questionKey);

        typingQuizRef.child(questionKey).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d(TAG, "Snapshot tồn tại: " + snapshot.exists());

                if (snapshot.exists()) {
                    String questionText = snapshot.child("question").getValue(String.class);
                    correctAnswer = snapshot.child("answer_text").getValue(String.class);

                    if (questionText != null && correctAnswer != null) {
                        txtQuestion.setText(questionText);

                        // 👇 Cập nhật số thứ tự câu hỏi
                        txtQuestionIndex.setText("Câu hỏi " + (currentIndex + 1) + " / " + totalQuestions);
                    } else {
                        Toast.makeText(TypingQuizActivity.this, "Câu hỏi hoặc đáp án bị thiếu", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } else {
                    Toast.makeText(TypingQuizActivity.this, "Không tìm thấy câu hỏi", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG, "Lỗi Firebase: ", error.toException());
                Toast.makeText(TypingQuizActivity.this, "Lỗi Firebase: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void checkAnswer() {
        String userInput = edtAnswer.getText().toString().trim();
        Log.d(TAG, "Người dùng nhập: " + userInput);

        if (userInput.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đáp án", Toast.LENGTH_SHORT).show();
            return;
        }

        if (userInput.equalsIgnoreCase(correctAnswer.trim())) {
            score++; // ✅ Tăng điểm nếu đúng
            Toast.makeText(this, "✅ Chính xác!", Toast.LENGTH_SHORT).show();
            imgMascot.setImageResource(R.drawable.mascot_happy);
            imgMascot.startAnimation(AnimationUtils.loadAnimation(this, R.anim.mascot_jump));
        } else {
            Toast.makeText(this, "❌ Sai rồi. Đáp án đúng là: " + correctAnswer, Toast.LENGTH_LONG).show();
            imgMascot.setImageResource(R.drawable.mascot_sad);
            imgMascot.startAnimation(AnimationUtils.loadAnimation(this, R.anim.mascot_shake));
        }

        currentIndex++;
        if (currentIndex < totalQuestions) {
            imgMascot.postDelayed(() -> loadQuestion(currentIndex), 1500);
        } else {
            imgMascot.postDelayed(() -> {
                Intent intent = new Intent(TypingQuizActivity.this, ResultActivity.class);
                intent.putExtra("score", score);
                intent.putExtra("total", totalQuestions);
                intent.putExtra("topic", "TypingQuiz");
                startActivity(intent);
                finish();
            }, 1500); // Chờ 1.5 giây rồi mới chuyển màn hình
        }
    }

}