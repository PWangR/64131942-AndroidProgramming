package dp.wang;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText so1, so2, ketqua;
    Button bt1, bt2, bt3, bt4;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TimDieuKhien();
        bt1.setOnClickListener(NutCong);
        bt2.setOnClickListener(NutTru);
        bt3.setOnClickListener(NutNhan);
        bt4.setOnClickListener(NutChia);
    }

    View.OnClickListener NutCong = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            XuLy_Cong();
        }
    };
    View.OnClickListener NutTru = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            XuLy_Tru();
        }
    };
    View.OnClickListener NutNhan = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            XuLy_Nhan();
        }
    };
    View.OnClickListener NutChia = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            XuLy_Chia();
        }
    };

    void TimDieuKhien()
    {
        so1 = findViewById(R.id.edt1);
        so2 = findViewById(R.id.edt2);
        ketqua = findViewById(R.id.edtketqua);
        bt1 = findViewById(R.id.btn1);
        bt2 = findViewById(R.id.btn2);
        bt3 = findViewById(R.id.btn3);
        bt4 = findViewById(R.id.btn4);
    }

    public void XuLy_Cong( )
    {
        String sothu1 =  so1.getText().toString();
        String sothu2 =  so2.getText().toString();

        float a = Float.parseFloat(sothu1);
        float b = Float.parseFloat(sothu2);

        float  tong = a + b;


        String chuoikq = String.valueOf(tong);

        ketqua.setText(chuoikq);
    }
    public void XuLy_Tru( )
    {
        String sothu1 =  so1.getText().toString();
        String sothu2 =  so2.getText().toString();

        float a = Float.parseFloat(sothu1);
        float b = Float.parseFloat(sothu2);

        float  hieu = a - b;


        String chuoikq = String.valueOf(hieu);

        ketqua.setText(chuoikq);
    }
    public void XuLy_Nhan( )
    {
        String sothu1 =  so1.getText().toString();
        String sothu2 =  so2.getText().toString();

        float a = Float.parseFloat(sothu1);
        float b = Float.parseFloat(sothu2);

        float  tich = a * b;


        String chuoikq = String.valueOf(tich);

        ketqua.setText(chuoikq);
    }
    public void XuLy_Chia()
    {
        String sothu1 =  so1.getText().toString();
        String sothu2 =  so2.getText().toString();

        float a = Float.parseFloat(sothu1);
        float b = Float.parseFloat(sothu2);

        float  thuong = a / b;


        String chuoikq = String.valueOf(thuong);

        ketqua.setText(chuoikq);
    }


}