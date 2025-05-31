package dp.wang;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private RecyclerView recyclerViewHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        recyclerViewHistory = findViewById(R.id.recyclerViewHistory);
        recyclerViewHistory.setLayoutManager(new LinearLayoutManager(this));

        // Lấy danh sách điểm từ SharedPreferences
        List<ScoreEntry> scores = ScoreStorage.getSavedScores(this);
        Log.d("HistoryActivity", "Tổng số điểm: " + scores.size());


        // Gán vào adapter
        HistoryAdapter adapter = new HistoryAdapter(scores);
        recyclerViewHistory.setAdapter(adapter);
    }
}
