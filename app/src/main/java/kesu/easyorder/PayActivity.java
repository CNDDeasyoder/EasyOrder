package kesu.easyorder;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by thinhle on 9/16/17.
 */

public class PayActivity extends AppCompatActivity {
    private ImageButton btnBack;
    private TextView tvTongTien;
    private TextView headline;
    private Button btnThanhToan;
    ListView lvThanhToan;
    ArrayList<MonAn> list;
    ThanhToanAdapter thanhToanAdapter;
    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        DatabaseReference hien = FirebaseDatabase.getInstance().getReference();
        hien.child("danhSachBanAn").child("ban"+ SetInforActivity.banSo).child("state")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int check = dataSnapshot.getValue(int.class);
                        if (check == 0) {
                            Intent mIntent = new Intent(PayActivity.this,Thanks.class);
                            startActivity(mIntent);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

        btnBack = (ImageButton) findViewById(R.id.btn_nav_ttoan_thinh);
        tvTongTien = (TextView) findViewById(R.id.tv_tong_tien_thinh) ;
        headline = (TextView) findViewById(R.id.tv_headline_thanhtoan) ;
        lvThanhToan = (ListView) findViewById(R.id.lv_thanh_toan);
        btnThanhToan = (Button) findViewById(R.id.btn_thanh_toan_tong_thinh);

        Typeface f = Typeface.createFromAsset(getAssets(), "fonts/UVNBanhMi.TTF");
        headline.setTypeface(f);
        btnThanhToan.setTypeface(f);

        lvThanhToan.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                view.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        list = new ArrayList<>();

        DatabaseReference temp = mData.child("danhSachBanAn").child("ban" + SetInforActivity.banSo).child("khachHang");
        temp.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                for (DataSnapshot ds : dataSnapshot.getChildren())
                {
                    ThongTinMonAn thongTinMonAn = ds.getValue(ThongTinMonAn.class);
                    MonAn monAn = new MonAn(thongTinMonAn.getTen_mon(), thongTinMonAn.dang_chon, thongTinMonAn.gia);
                    list.add(monAn);

                }
                thanhToanAdapter = new ThanhToanAdapter(PayActivity.this, R.layout.dong_thanh_toan, list);
                lvThanhToan.setAdapter(thanhToanAdapter);

                // ham tinh tong tien can thanh toan
                int tong = 0;
                for (int i = 0; i < list.size(); i++)
                {
                    tong = tong + list.get(i).getDonGia() * list.get(i).getSoLuong();
                }
                tvTongTien.setText(String.valueOf(tong));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



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
                mData.child("danhSachBanAn").child("ban" + SetInforActivity.banSo).child("state").setValue(2);
                Toast.makeText(PayActivity.this, "Yêu cầu đã được gửi. Vui lòng đợi trong giây lát!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
