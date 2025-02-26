package com.example.ex3_simplesumapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity
{
    //khai bao
    EditText so1;
    EditText so2;
    EditText ketqua;
    Button  nutcong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TimDieuKhien();
    }

    //TIM DIEU KHIEN
    void TimDieuKhien()
    {
         so1 = (EditText)findViewById(R.id.edt1);
         so2 = (EditText)findViewById(R.id.edt2);
         ketqua = (EditText)findViewById(R.id.edtketqua);
         nutcong = (Button) findViewById(R.id.btn1);

    }
    //Xu ly cong
    public void  XuLy_Cong(View v)
    {
       String sothu1 =  so1.getText().toString();
       String sothu2 =  so2.getText().toString();

       float a = Float.parseFloat(sothu1);
       float b = Float.parseFloat(sothu2);

       float  tong = a + b;


       String chuoikq = String.valueOf(tong);

       ketqua.setText(chuoikq);
    }

}
