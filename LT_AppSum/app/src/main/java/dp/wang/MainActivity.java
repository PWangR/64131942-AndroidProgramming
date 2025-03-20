package dp.wang;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView tvrd1, tvrd2;
    Button btnKT;
    EditText edtkq;

    int soLuong = 10;
    int diem = 0;
    int i = 0;
    private Random rd = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Find();
        TaoQuiz();
        btnKT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Check();
            }
        });

    }

    void Find()
    {
        tvrd1 = findViewById(R.id.tvRanDom1);
        tvrd2 = findViewById(R.id.tvRanDom2);
        edtkq  =  findViewById(R.id.edtKQ);
        btnKT = findViewById(R.id.btnCheck);
    }

    void TaoQuiz()
    {
        int a = rd.nextInt(100);
        int b = rd.nextInt(100);
        tvrd1.setText(String.valueOf(a));
        tvrd2.setText(String.valueOf(b));
        edtkq.setText("");
    }


    Integer Check()
    {
        int a = Integer.parseInt(tvrd1.getText().toString());
        int b = Integer.parseInt(tvrd2.getText().toString());
        int da = Integer.parseInt(edtkq.getText().toString());

        if(da == a + b)
        {
            Toast.makeText(MainActivity.this,"Chính Xác", Toast.LENGTH_SHORT).show();
            return 1;
        }
        else
            Toast.makeText(MainActivity.this,"Saiiiiiii", Toast.LENGTH_SHORT).show();
        return 0;
    }

}