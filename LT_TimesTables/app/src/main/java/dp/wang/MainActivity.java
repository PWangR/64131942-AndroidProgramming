package dp.wang;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText inputNumber;
    Button calculateButton;
    TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputNumber = findViewById(R.id.input_number);
        calculateButton = findViewById(R.id.calculate_button);
        resultText = findViewById(R.id.result_text);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateMultiplicationTable();
            }
        });
    }

    private void calculateMultiplicationTable() {
        String input = inputNumber.getText().toString().trim();
        if (input.isEmpty()) {
            Toast.makeText(this, "Please enter a number!", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int number = Integer.parseInt(input);
            StringBuilder result = new StringBuilder();

            for (int i = 1; i <= 10; i++) {
                result.append(number).append(" x ").append(i).append(" = ").append(number * i).append("\n");
            }

            resultText.setText(result.toString());
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid input! Please enter a valid number.", Toast.LENGTH_SHORT).show();
        }
    }
}
