package com.example.ex_recycleview;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    LandScapeAdapter landScapeAdapter;
    ArrayList<LandScape> lstLand;
    RecyclerView recyclerViewLand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstLand = getDataForRecycleView();

        recyclerViewLand = findViewById(R.id.rvLand);

        RecyclerView.LayoutManager layoutLinear = new LinearLayoutManager(this);
        recyclerViewLand.setLayoutManager(layoutLinear);

//        RecyclerView.LayoutManager layoutLinear = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//        recyclerViewLand.setLayoutManager(layoutLinear);


        landScapeAdapter = new LandScapeAdapter(this, lstLand);

        recyclerViewLand.setAdapter(landScapeAdapter);
    }

    //
    ArrayList<LandScape> getDataForRecycleView()
    {
        ArrayList<LandScape> dsData = new ArrayList<LandScape>();
        dsData.add(new LandScape("Đại học Nha Trang", "img1"));
        dsData.add(new LandScape("Đại học Nha Trang", "img2"));
        dsData.add(new LandScape("Đại học Nha Trang", "img3"));
        dsData.add(new LandScape("Đại học Nha Trang", "img4"));
        dsData.add(new LandScape("Đại học Nha Trang", "img5"));
        dsData.add(new LandScape("Đại học Nha Trang", "img6"));
        return dsData;
    }
}