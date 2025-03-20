package dp.wang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class GuessNumberActivity extends AppCompatActivity {

    private int randomNumber;
    private EditText numberInput;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_number);

        numberInput = findViewById(R.id.numberInput);
        resultText = findViewById(R.id.resultText);
        Button guessButton = findViewById(R.id.guessButton);
        Button backButton = findViewById(R.id.backButton);

        Random random = new Random();
        randomNumber = random.nextInt(100) + 1;

        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkGuess();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuessNumberActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void checkGuess() {
        String input = numberInput.getText().toString();

        if (input.isEmpty()) {
            resultText.setText("Vui lòng nhập một số!");
            return;
        }

        int guess = Integer.parseInt(input);

        if (guess < 1 || guess > 100) {
            resultText.setText("Số phải từ 1 đến 100!");
        } else if (guess < randomNumber) {
            resultText.setText("Quá thấp! Thử lại.");
        } else if (guess > randomNumber) {
            resultText.setText("Quá cao! Thử lại.");
        } else {
            resultText.setText("Chính xác! Chúc mừng bạn!");
            AlphaAnimation blink = new AlphaAnimation(0.0f, 1.0f);
            blink.setDuration(300);
            blink.setRepeatMode(AlphaAnimation.REVERSE);
            blink.setRepeatCount(5);
            resultText.startAnimation(blink);
        }
    }
}