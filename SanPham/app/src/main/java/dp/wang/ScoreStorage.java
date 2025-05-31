package dp.wang;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ScoreStorage {
    private static final String PREF_NAME = "quiz_scores";
    private static final String KEY_SCORES = "latest_scores";
    private static final int MAX_SIZE = 10;

    public static void saveScore(Context context, String topic, int score) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String oldScores = prefs.getString(KEY_SCORES, "");

        List<String> scoreList = new ArrayList<>(Arrays.asList(oldScores.split(",")));
        if (scoreList.size() == 1 && scoreList.get(0).isEmpty()) {
            scoreList.clear();
        }

        long date = System.currentTimeMillis(); // lấy thời gian hiện tại
        scoreList.add(0, topic + "|" + score + "|" + date); // lưu thêm thời gian

        if (scoreList.size() > MAX_SIZE) {
            scoreList = scoreList.subList(0, MAX_SIZE);
        }

        String newScores = String.join(",", scoreList);
        prefs.edit().putString(KEY_SCORES, newScores).apply();
    }




    public static List<ScoreEntry> getSavedScores(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String saved = prefs.getString(KEY_SCORES, "");
        List<ScoreEntry> scores = new ArrayList<>();

        if (!saved.isEmpty()) {
            for (String entry : saved.split(",")) {
                String[] parts = entry.split("\\|");
                if (parts.length == 3) {
                    try {
                        String topic = parts[0];
                        int score = Integer.parseInt(parts[1]);
                        long date = Long.parseLong(parts[2]);
                        scores.add(new ScoreEntry(topic, score, date));
                    } catch (NumberFormatException ignored) {}
                }
            }
        }

        Log.d("DEBUG", "Tổng số điểm: " + scores.size());
        return scores;
    }





}

