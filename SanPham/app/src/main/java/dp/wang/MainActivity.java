package dp.wang;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    TextView txtTotalStars;
    QuizProgressManager progressManager;

    BottomNavigationView bottom_nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Hiển thị tổng số sao
        txtTotalStars = findViewById(R.id.txtTotalStars);
        progressManager = new QuizProgressManager(this);
        int stars = progressManager.getTotalStars();
        txtTotalStars.setText("🌟 Sao đạt được: " + stars);

        bottom_nav = findViewById(R.id.bottomNavigation);
        bottom_nav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_start) {
                startActivity(new Intent(this, UploadQuestionsActivity.class));
                return true;
            } else if (id == R.id.nav_topic) {
                startActivity(new Intent(this, ChooseTopicActivity.class));
                return true;
            } else if (id == R.id.nav_history) {
                startActivity(new Intent(this, HistoryActivity.class));
                return true;
            }

            return false;
        });
    }
}
