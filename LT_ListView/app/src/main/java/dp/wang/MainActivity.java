package dp.wang;

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

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ArrayList<String> dsHoa = new ArrayList<>();
        dsHoa.add("Hoa Hồng");
        dsHoa.add("Hoa Đào");
        dsHoa.add("Hoa Lan");
        dsHoa.add("Hoa Cúc");
        dsHoa.add("Hoa Sen");
        dsHoa.add("Hoa Mai");

        ArrayAdapter<String> adapterHoa = new ArrayAdapter<>(
         this,
                android.R.layout.simple_list_item_1,
                dsHoa
        );

        ListView ds = findViewById(R.id.rc1);
        ds.setAdapter(adapterHoa);

        ds.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value = adapterHoa.getItem(position);
                Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();
            }
        });



    }
}