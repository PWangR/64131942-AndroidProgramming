package dp.wang;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivityHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intentLogin = getIntent();
        String tenDN =  intentLogin.getStringExtra("ten_dang_nhap");

        TextView tvDN = findViewById(R.id.tvUseName);
        tvDN.setText(tenDN);

    }
}