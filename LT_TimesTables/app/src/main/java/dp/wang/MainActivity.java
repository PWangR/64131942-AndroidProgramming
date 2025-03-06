package dp.wang;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultText = findViewById(R.id.result_text);

        int[] buttonIds = {
                R.id.button_1, R.id.button_2, R.id.button_3, R.id.button_4, R.id.button_5,
                R.id.button_6, R.id.button_7, R.id.button_8, R.id.button_9, R.id.button_10
        };

        for (int i = 0; i < buttonIds.length; i++) {
            final int number = i + 1;
            Button button = findViewById(buttonIds[i]);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showMultiplicationTable(number);
                }
            });
        }
    }

    private void showMultiplicationTable(int number) {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= 10; i++) {
            result.append(number).append(" x ").append(i).append(" = ")
                    .append(number * i).append("\n");
        }

        // Đặt text trước
        resultText.setText(result.toString());

        // Tạo hiệu ứng động: Fade In + Scale
        resultText.setAlpha(0f); // Ban đầu mờ hoàn toàn
        resultText.setScaleX(0.8f); // Thu nhỏ theo chiều ngang
        resultText.setScaleY(0.8f); // Thu nhỏ theo chiều dọc

        // Thực hiện animation
        resultText.animate()
                .alpha(1f) // Hiển thị rõ dần
                .scaleX(1f) // Phóng to về kích thước bình thường
                .scaleY(1f) // Phóng to về kích thước bình thường
                .setDuration(300) // Thời gian 300ms
                .start();
    }
}