package thigk2.duongphuquang;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SoThichAdapter extends RecyclerView.Adapter<SoThichAdapter.ItemSTHolder> {

    Context context;
    ArrayList<SoThich> lstData;

    public SoThichAdapter(Context context, ArrayList<SoThich> lstData) {
        this.context = context;
        this.lstData = lstData;
    }

    @NonNull
    @Override
    public ItemSTHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater cai_bom = LayoutInflater.from(context);
        View vItem = cai_bom.inflate(R.layout.sothich, parent, false);
        ItemSTHolder viewHolderCreate = new ItemSTHolder(vItem);
        return viewHolderCreate;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemSTHolder holder, int position) {
        SoThich soThichHienThi = lstData.get(position);

        String text = soThichHienThi.getTvST();
        String tenAnh = soThichHienThi.getImgST();

        holder.tvST.setText(text);
            String packagename = holder.itemView.getContext().getPackageName();
            int imgId = holder.itemView.getResources().getIdentifier(tenAnh, "mipmap", packagename);
        holder.imgST.setImageResource(imgId);

    }

    @Override
    public int getItemCount() {
        return lstData.size();
    }

    class ItemSTHolder extends RecyclerView.ViewHolder
    {
        TextView tvST;
        ImageView imgST;


        public ItemSTHolder(@NonNull View itemView) {
            super(itemView);
            tvST = itemView.findViewById(R.id.tvSoThich);
            imgST = itemView.findViewById(R.id.imgSoThich);
        }
    }
}
