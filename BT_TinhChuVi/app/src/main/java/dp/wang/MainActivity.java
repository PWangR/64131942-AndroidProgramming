package dp.wang;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText nhap1, nhap2, ketQua;
    Button btnChuViHCN, btnDienTichHCN, btnChuViHV, btnDienTichHV,
            btnChuViTG, btnDienTichTG, btnChuViHT, btnDienTichHT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TimDieuKhien();
    }

    void TimDieuKhien() {
        nhap1 = findViewById(R.id.edtNhap1);
        nhap2 = findViewById(R.id.edtNhap2);
        ketQua = findViewById(R.id.edtKetQua);
        btnChuViHCN = findViewById(R.id.btnChuViHCN);
        btnDienTichHCN = findViewById(R.id.btnDienTichHCN);
        btnChuViHV = findViewById(R.id.btnChuViHV);
        btnDienTichHV = findViewById(R.id.btnDienTichHV);
    }

    // Hình chữ nhật
    public void ChuViHCN(View v) {
        float chieuDai = Float.parseFloat(nhap1.getText().toString());
        float chieuRong = Float.parseFloat(nhap2.getText().toString());
        float chuVi = 2 * (chieuDai + chieuRong);
        ketQua.setText(String.valueOf(chuVi));
    }

    public void DienTichHCN(View v) {
        float chieuDai = Float.parseFloat(nhap1.getText().toString());
        float chieuRong = Float.parseFloat(nhap2.getText().toString());
        float dienTich = chieuDai * chieuRong;
        ketQua.setText(String.valueOf(dienTich));
    }

    // Hình vuông
    public void ChuViHV(View v) {
        float canh = Float.parseFloat(nhap1.getText().toString());
        float chuVi = 4 * canh;
        ketQua.setText(String.valueOf(chuVi));
    }

    public void DienTichHV(View v) {
        float canh = Float.parseFloat(nhap1.getText().toString());
        float dienTich = canh * canh;
        ketQua.setText(String.valueOf(dienTich));
    }

}