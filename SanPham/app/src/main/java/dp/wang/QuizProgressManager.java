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

    // Gọi khi người chơi đạt điểm tuyệt đối
    public void addStarIfPerfectScore(int score) {
        if (score >= 10) {
            int currentStars = getTotalStars();
            int newStars = currentStars + 1;
            editor.putInt("total_stars", newStars);
            updateAchievement(newStars); // Cập nhật danh hiệu
            editor.apply();
        }
    }

    public int getTotalStars() {
        return sharedPreferences.getInt("total_stars", 0);
    }

    // Cập nhật danh hiệu dựa vào tổng số sao
    private void updateAchievement(int stars) {
        String achievement = "";

        if (stars >= 30) {
            achievement = "Code Master";
        } else if (stars >= 20) {
            achievement = "Lập trình viên";
        } else if (stars >= 10) {
            achievement = "Coder tập sự";
        } else if (stars >= 5) {
            achievement = "Người mới học";
        }

        if (!achievement.isEmpty()) {
            editor.putString("achievement", achievement);
        }
    }

    public String getAchievementTitle() {
        return sharedPreferences.getString("achievement", "Chưa có danh hiệu");
    }

    public void resetStars() {
        editor.putInt("total_stars", 0);
        editor.remove("achievement"); // Xoá danh hiệu nếu có
        editor.apply();
    }

}

