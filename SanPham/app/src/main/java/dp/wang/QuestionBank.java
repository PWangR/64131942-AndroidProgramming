package dp.wang;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QuestionBank {

    // Dữ liệu câu hỏi từ Firebase
    private static final HashMap<String, List<Question>> dynamicQuestions = new HashMap<>();

    // Gọi từ UploadQuestionsActivity sau khi tải câu hỏi từ Firebase
    public static void setQuestionsFromFirebase(String topic, List<Question> questions) {
        dynamicQuestions.put(topic, questions);
        Log.d("QuestionBank", "Đã lưu " + questions.size() + " câu hỏi cho topic: " + topic);
    }


    // Trả về danh sách câu hỏi, ưu tiên từ Firebase, nếu không có trả về danh sách rỗng
    public static List<Question> getQuestionsByTopic(String topic) {
        Log.d("QuestionBank", "Yêu cầu lấy câu hỏi cho topic: " + topic);
        if (dynamicQuestions.containsKey(topic)) {
            List<Question> list = dynamicQuestions.get(topic);
            Log.d("QuestionBank", "Đã tìm thấy " + list.size() + " câu hỏi cho topic: " + topic);
            return list;
        }

        Log.d("QuestionBank", "Không tìm thấy câu hỏi cho topic: " + topic);
        return new ArrayList<>();
    }

}
