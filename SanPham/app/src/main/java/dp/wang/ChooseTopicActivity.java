package dp.wang;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ChooseTopicActivity extends AppCompatActivity {

    private static final String TAG = "ChooseTopicActivity";
    private LinearLayout linearLayoutTopics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_topic);

        linearLayoutTopics = findViewById(R.id.layoutContainer);

        fetchTopicsFromFirebase();
    }

    private void fetchTopicsFromFirebase() {
        DatabaseReference topicsRef = FirebaseDatabase.getInstance().getReference("Questions");

        topicsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (!snapshot.exists()) {
                    Toast.makeText(ChooseTopicActivity.this, "Không có chủ đề nào trong Firebase", Toast.LENGTH_SHORT).show();
                    return;
                }

                List<TopicData> topicList = new ArrayList<>();

                for (DataSnapshot topicSnapshot : snapshot.getChildren()) {
                    String topicName = topicSnapshot.getKey();
                    int count = (int) topicSnapshot.getChildrenCount();

                    // Bỏ qua chủ đề "TypingQuiz"
                    if (topicName != null && !topicName.equals("TypingQuiz")) {
                        topicList.add(new TopicData(topicName, count));
                    }
                }

                createTopicCards(topicList);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(ChooseTopicActivity.this, "Lỗi tải chủ đề từ Firebase", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void createTopicCards(List<TopicData> topicList) {
        linearLayoutTopics.removeAllViews();

        for (TopicData topic : topicList) {
            View cardView = getLayoutInflater().inflate(R.layout.item_topic_card, linearLayoutTopics, false);

            TextView txtTopicName = cardView.findViewById(R.id.txtTopicName);
            TextView txtQuestionCount = cardView.findViewById(R.id.txtQuestionCount);

            txtTopicName.setText(topic.name);
            txtQuestionCount.setText(topic.questionCount + " câu hỏi");

            cardView.setOnClickListener(v -> downloadQuestionsAndStartQuiz(topic.name));

            linearLayoutTopics.addView(cardView);
        }
    }

    private void downloadQuestionsAndStartQuiz(String selectedTopic) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Questions").child(selectedTopic);

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

                QuestionBank.setQuestionsFromFirebase(selectedTopic, downloadedQuestions);
                Log.d(TAG, "Lưu vào QuestionBank: " + downloadedQuestions.size() + " câu hỏi");

                Intent intent = new Intent(ChooseTopicActivity.this, QuizActivity.class);
                intent.putExtra("topic", selectedTopic);
                startActivity(intent);
                finish();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ChooseTopicActivity.this, "Lỗi tải dữ liệu", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "Lỗi tải dữ liệu: " + error.getMessage());
            }
        });
    }

    private static class TopicData {
        String name;
        int questionCount;

        TopicData(String name, int questionCount) {
            this.name = name;
            this.questionCount = questionCount;
        }
    }
}
