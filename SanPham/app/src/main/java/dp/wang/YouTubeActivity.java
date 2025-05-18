package dp.wang;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class YouTubeActivity extends AppCompatActivity {

    private static final String CHANNEL_URL = "https://www.youtube.com/@TinhocTiiL"; // Thay thành link kênh của bạn

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_tube);

        Button btnOpenYouTube = findViewById(R.id.btnOpenYouTube);
        btnOpenYouTube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openYouTubeChannel();
            }
        });
    }

    private void openYouTubeChannel() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(CHANNEL_URL));
        intent.setPackage("com.google.android.youtube"); // ưu tiên mở bằng app YouTube

        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            // Nếu chưa cài app YouTube, mở bằng trình duyệt web
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(CHANNEL_URL));
            startActivity(webIntent);
        }
    }
}
