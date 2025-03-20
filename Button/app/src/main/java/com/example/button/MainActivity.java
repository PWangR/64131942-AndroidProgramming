package com.example.button;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView num1Text, num2Text, timerText, scoreText;
    private EditText answerInput;
    private int score = 0;
    private int timeLeft = 30;
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        num1Text = findViewById(R.id.num1Text);
        num2Text = findViewById(R.id.num2Text);
        answerInput = findViewById(R.id.answerInput);
        timerText = findViewById(R.id.timerText);
        scoreText = findViewById(R.id.scoreText);

        generateNewQuestion();

        // Number buttons
        int[] buttonIds = {
                R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5,
                R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9, R.id.btn10
        };

        for (int id : buttonIds) {
            Button button = findViewById(id);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String currentText = answerInput.getText().toString();
                    String buttonText = ((Button)v).getText().toString();
                    answerInput.setText(currentText + buttonText);
                }
            });
        }

        // Clear button (xóa từng ký tự)
        Button clearButton = findViewById(R.id.btnClear);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentText = answerInput.getText().toString();
                if (!currentText.isEmpty()) {
                    answerInput.setText(currentText.substring(0, currentText.length() - 1));
                }
            }
        });

        // Clear all button (xóa toàn bộ)
        Button clearAllButton = findViewById(R.id.btnClearAll);
        clearAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answerInput.setText("");
            }
        });

        // Check button
        Button checkButton = findViewById(R.id.checkButton);
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });

        // Start timer
        startTimer();
    }

    private void generateNewQuestion() {
        int num1 = random.nextInt(100); // Số ngẫu nhiên từ 0-99
        int num2 = random.nextInt(100); // Số ngẫu nhiên từ 0-99
        num1Text.setText(String.valueOf(num1));
        num2Text.setText(String.valueOf(num2));
        answerInput.setText("");
    }

    private void checkAnswer() {
        try {
            int num1 = Integer.parseInt(num1Text.getText().toString());
            int num2 = Integer.parseInt(num2Text.getText().toString());
            int userAnswer = Integer.parseInt(answerInput.getText().toString());

            if (userAnswer == num1 + num2) { // Kiểm tra phép cộng
                score += 10;
                scoreText.setText("Điểm: " + score);
            }
            generateNewQuestion();
        } catch (NumberFormatException e) {
            // Handle empty or invalid input
            return;
        }
    }

    private void startTimer() {
        new CountDownTimer(30000, 1000) {
            public void onTick(long millisUntilFinished) {
                timeLeft = (int)(millisUntilFinished / 1000);
                timerText.setText("Thời gian: " + timeLeft);
            }

            public void onFinish() {
                timerText.setText("Hết giờ!");
                // Có thể thêm logic khi hết giờ
            }
        }.start();
    }
}