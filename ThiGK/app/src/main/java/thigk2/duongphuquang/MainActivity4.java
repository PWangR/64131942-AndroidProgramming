package thigk2.duongphuquang;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity4 extends AppCompatActivity {

    SoThichAdapter soThichAdapter;
    ArrayList<SoThich> soThiches;
    RecyclerView recyclerView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        soThiches = getDaTa();

        recyclerView = findViewById(R.id.recyclerView);

        RecyclerView.LayoutManager layoutLinear = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutLinear);

        soThichAdapter = new SoThichAdapter(this, soThiches);

        recyclerView.setAdapter(soThichAdapter);
    }
    ArrayList<SoThich> getDaTa()
    {
        ArrayList<SoThich> dsData = new ArrayList<SoThich>();
        dsData.add(new SoThich("ĐÁNH GIÁ NĂNG LỰC", "img2"));
        dsData.add(new SoThich("XÂY CÔNG TRÌNH KỶ NIỆM", "img3"));
        dsData.add(new SoThich("DON RÁC - LÀM SẠCH MÔI TRƯỜNG", "img4"));
        dsData.add(new SoThich("TRỒNG CÂY XANH", "img5"));
        dsData.add(new SoThich("PHÁT TRIỂN THÀNH PHỐ", "img6"));
        return  dsData;
    }
}