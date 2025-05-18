package dp.wang;

import android.content.Context;
import android.content.SharedPreferences;

public class QuizProgressManager {
    private static final String PREF_NAME = "quiz_progress";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public QuizProgressManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    // Lưu tổng số sao (cộng dồn)
    public void addStarIfPerfectScore(int score) {
        if (score >= 10) {
            int currentStars = getTotalStars();
            editor.putInt("total_stars", currentStars + 1);
            editor.apply();
        }
    }

    public int getTotalStars() {
        return sharedPreferences.getInt("total_stars", 0);
    }

    // (các hàm khác: saveHighScore, getHighScore, incrementQuizCount,... vẫn giữ nguyên)
}

