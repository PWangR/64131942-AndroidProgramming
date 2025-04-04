package com.example.lt_itemactivity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ItemActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item); // Layout của ItemActivity

        // Bước 2: Nhận dữ liệu từ Intent
        String content = getIntent().getStringExtra("content");

        // Hiển thị nội dung lên TextView
        TextView textView = findViewById(R.id.textViewContent);
        textView.setText(content);
    }
}
