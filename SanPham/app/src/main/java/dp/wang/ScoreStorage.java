package dp.wang;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        // Thêm điểm mới với chủ đề
        scoreList.add(0, topic + "|" + score);

        // Giữ tối đa 10 dòng
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
                if (parts.length == 2) {
                    try {
                        scores.add(new ScoreEntry(parts[0], Integer.parseInt(parts[1])));
                    } catch (NumberFormatException ignored) {}
                }
            }
        }

        return scores;
    }



}

