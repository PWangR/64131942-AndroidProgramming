package com.example.ex_recycleview;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class LandScapeAdapter extends RecyclerView.Adapter<LandScapeAdapter.ItemLandHolder> {

    Context context;
    ArrayList<LandScape> lstData;

    public LandScapeAdapter(Context context, ArrayList<LandScape> lstData) {
        this.context = context;
        this.lstData = lstData;
    }

    @NonNull
    @Override
    public ItemLandHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater cai_bom = LayoutInflater.from(context);
        View vItem =  cai_bom.inflate(R.layout.item_land, parent, false);
        ItemLandHolder viewholderCreate = new ItemLandHolder(vItem);
        return  viewholderCreate;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemLandHolder holder, int position) {
        // lay doi tuong hien thi
        LandScape landScapeHienThi = lstData.get(position);
        // trich thong tin
        String caption = landScapeHienThi.getCaption();
        String tenAnh = landScapeHienThi.getLandIMG();
        // Đặt vào các trường thông tin của holder
        holder.tvCaption.setText(caption);
        // đặt ảnh
            String packageName = holder.itemView.getContext().getPackageName();
            int imgID = holder.itemView.getResources().getIdentifier(tenAnh, "mipmap", packageName);
        holder.imgLand.setImageResource(imgID);
    }

    @Override
    public int getItemCount() {
        return lstData.size();
    }

    class ItemLandHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvCaption;
        ImageView imgLand;

        public ItemLandHolder(@NonNull View itemView) {
            super(itemView);
            tvCaption = itemView.findViewById(R.id.tvCaption);
            imgLand = itemView.findViewById(R.id.imgName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //lay vi tri
            int vitriduocchon = getAdapterPosition();
            LandScape pphantuduochon =  lstData.get(vitriduocchon);
            // bóc thông tin
            String ten = pphantuduochon.getCaption();
            String  tenFile = pphantuduochon.getLandIMG();
            // Toast tên
            String chuoiThongBao = "Bạn vừa click vào: " + ten;
            Toast.makeText(v.getContext(), chuoiThongBao, Toast.LENGTH_LONG).show();


        }
    }
}
