package dp.wang;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    TextView txtTotalStars;
    TextView txtAchievement;
    Button btnResetProgress;

    QuizProgressManager progressManager;
    BottomNavigationView bottom_nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ view
        txtTotalStars = findViewById(R.id.txtTotalStars);
        txtAchievement = findViewById(R.id.txtAchievement);
        btnResetProgress = findViewById(R.id.btnResetProgress);
        progressManager = new QuizProgressManager(this);

        // Hiển thị tổng sao
        int stars = progressManager.getTotalStars();
        txtTotalStars.setText("🌟 Sao đạt được: " + stars);

        // Hiển thị danh hiệu tương ứng
        String title = progressManager.getAchievementTitle();
        txtAchievement.setText("🏅 Danh hiệu: " + title);

        // Xử lý nút "Đặt lại tiến trình"
        btnResetProgress.setOnClickListener(view -> {
            new AlertDialog.Builder(this)
                    .setTitle("Xác nhận")
                    .setMessage("Bạn có chắc chắn muốn đặt lại tiến trình không?\nSố sao và danh hiệu sẽ bị xoá.")
                    .setPositiveButton("Có", (dialog, which) -> {
                        progressManager.resetStars();
                        txtTotalStars.setText("🌟 Sao đạt được: 0");
                        txtAchievement.setText("🏅 Danh hiệu: Chưa có danh hiệu");
                    })
                    .setNegativeButton("Không", null)
                    .show();
        });

        // Xử lý thanh điều hướng
        bottom_nav = findViewById(R.id.bottomNavigation);
        bottom_nav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_start) {
                startActivity(new Intent(this, AddQuestionActivity.class));
                return true;
            } else if (id == R.id.nav_topic) {
                startActivity(new Intent(this, ChooseTopicActivity.class));
                return true;
            } else if (id == R.id.nav_history) {
                startActivity(new Intent(this, HistoryActivity.class));
                return true;
            } else if (id == R.id.nav_typing) {
                startActivity(new Intent(this, TypingQuizActivity.class));
                return true;
            }

            return false;
        });
    }
}
