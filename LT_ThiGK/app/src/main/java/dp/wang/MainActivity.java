package dp.wang;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SoThichAdapter soThichAdapter;
    ArrayList<SoThich> soThiches;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        soThiches = getDaTa();

        recyclerView = findViewById(R.id.rvSoThich);

        RecyclerView.LayoutManager layoutLinear = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutLinear);

        soThichAdapter = new SoThichAdapter(this, soThiches);

        recyclerView.setAdapter(soThichAdapter);
    }
    ArrayList<SoThich> getDaTa()
    {
        ArrayList<SoThich> dsData = new ArrayList<SoThich>();
        dsData.add(new SoThich("Lắp mô hình", "mohinh"));
        dsData.add(new SoThich("Uống cà phê", "dicafe"));
        dsData.add(new SoThich("Nuôi cá", "nuoica"));
        dsData.add(new SoThich("Xem phim", "xemphim"));
        return  dsData;
    }


}