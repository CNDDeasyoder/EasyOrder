package kesu.easyorder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by thinhle on 9/16/17.
 */

public class PayActivity extends AppCompatActivity {
    private ImageButton btnBack;
    private TextView tvTongTien;
    private Button btnThanhToan;
    ListView lvThanhToan;
    ArrayList<MonAn> list;
    ThanhToanAdapter thanhToanAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        btnBack = (ImageButton) findViewById(R.id.btn_nav_ttoan_thinh);
        tvTongTien = (TextView) findViewById(R.id.tv_tong_tien_thinh) ;
        lvThanhToan = (ListView) findViewById(R.id.lv_thanh_toan);
        btnThanhToan = (Button) findViewById(R.id.btn_thanh_toan_tong_thinh);

        lvThanhToan.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                view.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        list = new ArrayList<>();

        list.add(new MonAn("Gà luộc", 2, 50));
        list.add(new MonAn("Gà kho", 3, 87));
        list.add(new MonAn("Gà nướng", 1, 67));
        list.add(new MonAn("Gà bóp", 2, 45));
        list.add(new MonAn("Gà lội sông", 3, 89));
        list.add(new MonAn("Gà tắm dầu", 2, 77));
        list.add(new MonAn("Gà bóp", 5, 77));
        list.add(new MonAn("Gà bóp", 2, 87));
        thanhToanAdapter = new ThanhToanAdapter(this, R.layout.dong_thanh_toan, list);
        lvThanhToan.setAdapter(thanhToanAdapter);

        // ham tinh tong tien can thanh toan
        int tong = 0;
        for (int i = 0; i < list.size(); i++)
        {
            tong = tong + list.get(i).getDonGia() * list.get(i).getSoLuong();
        }
        tvTongTien.setText(String.valueOf(tong));

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PayActivity.this, SelectionActivity.class);
                startActivity(intent);
            }
        });

        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // add more code
                Toast.makeText(PayActivity.this, "Yêu cầu đã được gửi. Vui lòng đợi trong giây lát!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
