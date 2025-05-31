package dp.wang;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private final List<ScoreEntry> scoreEntries;

    public HistoryAdapter(List<ScoreEntry> scoreEntries) {
        this.scoreEntries = scoreEntries;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_history, parent, false);
        return new HistoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        ScoreEntry entry = scoreEntries.get(position);
        holder.txtTopicName.setText("Chủ đề: " + entry.topic);
        holder.txtScore.setText("Điểm: " + entry.score);

        // Hiển thị ngày (giả sử entry.date là kiểu long - milliseconds)
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String formattedDate = sdf.format(entry.date);
        holder.txtDate.setText("Ngày: " + formattedDate);

        // Hiển thị sao nếu điểm đủ điều kiện
        if (entry.score >= 10) {
            holder.imgStar.setVisibility(View.VISIBLE);
        } else {
            holder.imgStar.setVisibility(View.INVISIBLE);
        }
    }


    @Override
    public int getItemCount() {
        return scoreEntries.size();
    }

    static class HistoryViewHolder extends RecyclerView.ViewHolder {
        TextView txtTopicName, txtDate, txtScore;
        ImageView imgStar, imgIcon;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTopicName = itemView.findViewById(R.id.txtTopicName);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtScore = itemView.findViewById(R.id.txtScore);
            imgStar = itemView.findViewById(R.id.imgStar);
            imgIcon = itemView.findViewById(R.id.imgIcon);
        }
    }
}
