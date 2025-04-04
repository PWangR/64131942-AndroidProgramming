package thigk2.duongphuquang;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity5 extends AppCompatActivity {
    EditText nhap1, nhap2, ketQua;
    Button btnChuViHCN, btnDienTichHCN, btnChuViHV, btnDienTichHV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
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
        ketQua.setText(String.format("Chu vi HCN %s", chuVi));
    }

    public void DienTichHCN(View v) {
        float chieuDai = Float.parseFloat(nhap1.getText().toString());
        float chieuRong = Float.parseFloat(nhap2.getText().toString());
        float dienTich = chieuDai * chieuRong;
        ketQua.setText(String.format("Diện tích HCN %s", dienTich));
    }

    // Hình vuông
    public void ChuViHV(View v) {
        float canh = Float.parseFloat(nhap1.getText().toString());
        float chuVi = 4 * canh;
        ketQua.setText(String.format("Chu vi HV %s", chuVi));
    }

    public void DienTichHV(View v) {
        float canh = Float.parseFloat(nhap1.getText().toString());
        float dienTich = canh * canh;
        ketQua.setText(String.format("Diện tích Hv %s", dienTich));
    }

}