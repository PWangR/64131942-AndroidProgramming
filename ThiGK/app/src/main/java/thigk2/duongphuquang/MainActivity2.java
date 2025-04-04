package thigk2.duongphuquang;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {
    EditText username;
    EditText password;

    EditText kq;
    Button loginButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        username = findViewById(R.id.editTextText);
        password = findViewById(R.id.editTextText2);
        loginButton = findViewById(R.id.button5);
        kq = findViewById(R.id.editTextText3);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().equals("30") && password.getText().toString().equals("1975"))
                {
                    kq.setText(String.format("ĐÚNG"));}
                else
                {
                    kq.setText(String.format("Sai"));}
                }
        });

    }
}