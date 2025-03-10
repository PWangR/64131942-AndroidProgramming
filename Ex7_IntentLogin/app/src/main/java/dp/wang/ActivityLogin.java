package dp.wang;

import android.content.Intent;
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

public class ActivityLogin extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText edtTenDN = findViewById(R.id.edtUseName);
        EditText edtMK = findViewById(R.id.edtPass);
        EditText edtMail = findViewById(R.id.edtMail);
        Button btnLogin = (Button) findViewById(R.id.btnOk);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String tenDangNhap = edtTenDN.getText().toString();
                String matKhau = edtMK.getText().toString();
                String mail = edtMail.getText().toString();

                if(tenDangNhap.equals("duongphuquang")&& matKhau.equals("2004"))
                {
                    Intent iquiz = new Intent(ActivityLogin.this, ActivityHome.class);
                    iquiz.putExtra("ten_dang_nhap", tenDangNhap);
                    iquiz.putExtra("mk_dang_nhap", matKhau);
                    iquiz.putExtra("mail", mail);

                    startActivity(iquiz);

                }
                else
                {
                    Toast.makeText(ActivityLogin.this, "BẠN NHẬP SAI THÔNG TIN", Toast.LENGTH_LONG).show();
                }
            }
        });



    }
}