package thigk2.duongphuquang;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        // Tạo danh sách bài hát
        ArrayList<String> dsBaiHat = new ArrayList<>();
        dsBaiHat.add("Tiến về Sài Gòn");
        dsBaiHat.add("Giải phóng miền Nam");
        dsBaiHat.add("Đất nước trọn niềm vui");
        dsBaiHat.add("Mùa xuân trên thành phó HCM");
        dsBaiHat.add("Tiến về Sài Gòn");
        dsBaiHat.add("Giải phóng miền Nam");
        dsBaiHat.add("Đất nước trọn niềm vui");
        dsBaiHat.add("Mùa xuân trên thành phố HCM");
        dsBaiHat.add("Tiến về Sài Gòn");
        dsBaiHat.add("Giải phóng miền Nam");
        dsBaiHat.add("Đất nước trọn niềm vui");
        dsBaiHat.add("Mùa xuân trên thành phó HCM");
        dsBaiHat.add("Tiến về Sài Gòn");
        dsBaiHat.add("Giải phóng miền Nam");
        dsBaiHat.add("Đất nước trọn niềm vui");
        dsBaiHat.add("Mùa xuân trên thành phố HCM");

        // Sử dụng layout mặc định của Android cho item trong ListView
        ArrayAdapter<String> adapterdsBaiHat = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1, // Layout mặc định cho item
                dsBaiHat
        );

        // Gắn adapter vào ListView
        ListView ds = findViewById(R.id.listView);
        ds.setAdapter(adapterdsBaiHat);

        // Xử lý sự kiện click vào item
        ds.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value = adapterdsBaiHat.getItem(position);
                Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();
            }
        });

    }
}