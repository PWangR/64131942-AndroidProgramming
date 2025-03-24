package dp.wang;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.app.TimePickerDialog;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView txtGioNgu, txtGioThuc, txtThoiGianNgu, txtGoiY;
    private Button btnTinhToan;
    private SharedPreferences luuTru;
    private int gioNgu, phutNgu, gioThuc, phutThuc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ ID từ layout
        txtGioNgu = findViewById(R.id.tvSleepTime);
        txtGioThuc = findViewById(R.id.tvWakeUpTime);
        txtThoiGianNgu = findViewById(R.id.tvSleepDuration);
        txtGoiY = findViewById(R.id.tvSleepSuggestion);
        btnTinhToan = findViewById(R.id.btnCalculate);

        // Khởi tạo SharedPreferences để lưu trữ dữ liệu
        luuTru = getSharedPreferences("DuLieuNgu", MODE_PRIVATE);

        // Tải thời gian đã lưu trước đó
        taiThoiGianDaLuu();

        // Xử lý sự kiện khi nhấn vào TextView để chọn giờ
        txtGioNgu.setOnClickListener(v -> hienThiChonGio(true));
        txtGioThuc.setOnClickListener(v -> hienThiChonGio(false));

        // Xử lý sự kiện khi nhấn nút tính toán
        btnTinhToan.setOnClickListener(v -> tinhThoiGianNgu());
    }

    private void taiThoiGianDaLuu() {
        // Lấy dữ liệu từ SharedPreferences, nếu không có thì dùng giá trị mặc định
        gioNgu = luuTru.getInt("gioNgu", 22);
        phutNgu = luuTru.getInt("phutNgu", 0);
        gioThuc = luuTru.getInt("gioThuc", 6);
        phutThuc = luuTru.getInt("phutThuc", 0);

        // Hiển thị thời gian lên TextView
        txtGioNgu.setText(String.format(Locale.getDefault(), "%02d:%02d", gioNgu, phutNgu));
        txtGioThuc.setText(String.format(Locale.getDefault(), "%02d:%02d", gioThuc, phutThuc));
    }

    private void hienThiChonGio(boolean laGioNgu) {
        // Lấy giờ hiện tại làm giá trị mặc định
        Calendar lich = Calendar.getInstance();
        int gio = laGioNgu ? gioNgu : gioThuc;
        int phut = laGioNgu ? phutNgu : phutThuc;

        // Hiển thị dialog chọn giờ
        TimePickerDialog hopThoaiChonGio = new TimePickerDialog(
                this,
                (view, gioDaChon, phutDaChon) -> {
                    if (laGioNgu) {
                        gioNgu = gioDaChon;
                        phutNgu = phutDaChon;
                        txtGioNgu.setText(String.format(Locale.getDefault(), "%02d:%02d", gioNgu, phutNgu));
                    } else {
                        gioThuc = gioDaChon;
                        phutThuc = phutDaChon;
                        txtGioThuc.setText(String.format(Locale.getDefault(), "%02d:%02d", gioThuc, phutThuc));
                    }
                },
                gio, phut, true
        );
        hopThoaiChonGio.show();
    }

    private void tinhThoiGianNgu() {
        // Lưu thời gian đã chọn vào SharedPreferences
        SharedPreferences.Editor bienTap = luuTru.edit();
        bienTap.putInt("gioNgu", gioNgu);
        bienTap.putInt("phutNgu", phutNgu);
        bienTap.putInt("gioThuc", gioThuc);
        bienTap.putInt("phutThuc", phutThuc);
        bienTap.apply();

        // Tính tổng số phút ngủ
        int tongPhut = (gioThuc * 60 + phutThuc) - (gioNgu * 60 + phutNgu);
        if (tongPhut < 0) tongPhut += 24 * 60; // Nếu giờ thức nhỏ hơn giờ ngủ, cộng thêm 1 ngày
        int gio = tongPhut / 60;
        int phut = tongPhut % 60;


        // Đưa ra gợi ý dựa trên thời gian ngủ và thói quen
        StringBuilder goiY = new StringBuilder();


        // Kiểm tra giờ đi ngủ (quá muộn: sau 01:00)
        if (gioNgu >= 1 && gioNgu < 6) { // Ngủ từ 1h đến 6h sáng
            goiY.append("\nBạn đi ngủ quá muộn! Hãy cố gắng ngủ trước 1 giờ sáng để cải thiện chất lượng giấc ngủ.");
        }
        // Kiểm tra giờ thức dậy (quá trễ: sau 10:00)
        if (gioThuc >= 9) { // Thức dậy từ 9h sáng trở đi
            goiY.append("\nBạn dậy khá trễ! Thức dậy sớm hơn (trước 10h) sẽ giúp bạn năng động hơn trong ngày.");
        }


        // Kiểm tra thời gian ngủ
        if (tongPhut < 90) {
            goiY.append("Bạn ngủ quá ít, điều này không tốt cho sức khỏe!");
        } else if (tongPhut < 180) {
            goiY.append("Bạn chỉ ngủ 1 chu kỳ, hãy cố gắng ngủ thêm để cơ thể phục hồi tốt hơn!");
        } else if (tongPhut < 360) {
            goiY.append("Giấc ngủ tạm ổn, nhưng hãy ngủ ít nhất 4 chu kỳ để cơ thể thực sự thoải mái.");
        } else if (tongPhut < 450) {
            goiY.append("Bạn có giấc ngủ tốt với 5 chu kỳ, đây là mức lý tưởng!");
        } else {
            goiY.append("Bạn ngủ rất đủ giấc! Hãy duy trì thói quen này để có sức khỏe tốt.");
        }

        // Hiển thị kết quả lên giao diện
        txtThoiGianNgu.setText(String.format(Locale.getDefault(), "Bạn đã ngủ: %d giờ %d phút", gio, phut));
        txtGoiY.setText(goiY.toString());

    }
}