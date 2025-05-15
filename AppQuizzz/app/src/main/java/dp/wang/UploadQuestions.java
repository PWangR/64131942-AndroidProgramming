package dp.wang;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class UploadQuestions extends AppCompatActivity {

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_questions);
        dbHelper = new DatabaseHelper(this);
        uploadQuestions();
    }

    private void uploadQuestions() {
        try {
            // Java Questions
            Question javaQ1 = new Question("Kiểu dữ liệu nào dùng để lưu số nguyên trong Java?",
                    new String[]{"int", "float", "String", "boolean"}, 0);
            Question javaQ2 = new Question("Phương thức nào dùng để in ra màn hình trong Java?",
                    new String[]{"print()", "System.out.println()", "write()", "display()"}, 1);

            // Python Questions
            Question pythonQ1 = new Question("Cú pháp nào dùng để khai báo hàm trong Python?",
                    new String[]{"function", "def", "fun", "proc"}, 1);
            Question pythonQ2 = new Question("Thư viện nào dùng để làm việc với mảng trong Python?",
                    new String[]{"pandas", "numpy", "matplotlib", "requests"}, 1);

            // C++ Questions
            Question cppQ1 = new Question("Ký hiệu nào dùng để truy cập thành viên lớp trong C++?",
                    new String[]{".", "->", "::", "&"}, 0);
            Question cppQ2 = new Question("Hàm nào là điểm bắt đầu của chương trình C++?",
                    new String[]{"start()", "main()", "begin()", "init()"}, 1);

            // C Questions
            Question cQ1 = new Question("Hàm nào dùng để in ra màn hình trong C?",
                    new String[]{"print()", "printf()", "write()", "display()"}, 1);
            Question cQ2 = new Question("Kiểu dữ liệu nào dùng để lưu số thực trong C?",
                    new String[]{"int", "float", "char", "void"}, 1);

            // Save to SQLite
            dbHelper.addQuestion(javaQ1, "Java");
            dbHelper.addQuestion(javaQ2, "Java");
            dbHelper.addQuestion(pythonQ1, "Python");
            dbHelper.addQuestion(pythonQ2, "Python");
            dbHelper.addQuestion(cppQ1, "C++");
            dbHelper.addQuestion(cppQ2, "C++");
            dbHelper.addQuestion(cQ1, "C");
            dbHelper.addQuestion(cQ2, "C");

            // In dữ liệu để kiểm tra
            dbHelper.logAllQuestions();

            Toast.makeText(this, "Đã lưu câu hỏi vào SQLite!", Toast.LENGTH_SHORT).show();
            finish();
        } catch (Exception e) {
            Toast.makeText(this, "Lỗi khi lưu: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}