package dp.wang;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Tạo danh sách bài hát
        ArrayList<String> dsBaiHat = new ArrayList<>();
        dsBaiHat.add("Dù cho tận thế");
        dsBaiHat.add("Dù cho tận thế vẫn");
        dsBaiHat.add("Dù cho tận thế vẫn yêu");
        dsBaiHat.add("Dù cho tận thế vẫn yêu em");

        // Sử dụng layout mặc định của Android cho item trong ListView
        ArrayAdapter<String> adapterdsBaiHat = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1, // Layout mặc định cho item
                dsBaiHat
        );

        // Gắn adapter vào ListView
        ListView ds = findViewById(R.id.lv1);
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