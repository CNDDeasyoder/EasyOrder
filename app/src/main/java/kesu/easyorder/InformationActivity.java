package kesu.easyorder;

import android.app.ProgressDialog;
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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by thinhle on 9/16/17.
 */

public class InformationActivity extends AppCompatActivity{

    private ImageButton btnBack;
    private Button btnThemMon;
    private Button btnThanhToan;
    private TextView banSo;
    private TextView tenKhachHang;
    private TextView headline;
    ListView lvThongTin;
    ArrayList<MonAn> monAnArrayList;
    ThongTinAdapter thongTinAdapter;
    private ProgressDialog dialog1;

    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor);
        dialog1 = new ProgressDialog(this);
        dialog1.setMessage("Đang lấy dữ liệu");
        dialog1.setCancelable(false);
        dialog1.show();

        btnBack = (ImageButton) findViewById(R.id.btn_nav_ttin_thinh);
        btnThanhToan = (Button) findViewById(R.id.btn_thanh_toan_thinh);
        btnThemMon = (Button) findViewById(R.id.btn_them_mon_thinh);
        lvThongTin = (ListView)findViewById(R.id.lv_thong_tin);
        banSo = (TextView) findViewById(R.id.tv_banso_thinh);
        tenKhachHang = (TextView) findViewById(R.id.tv_ten_kh_thinh);
        headline = (TextView) findViewById(R.id.tv_headline_thongtin);

        Typeface f = Typeface.createFromAsset(getAssets(), "fonts/UVNBanhMi.TTF");
        headline.setTypeface(f);
        btnThanhToan.setTypeface(f);
        btnThemMon.setTypeface(f);
        DatabaseReference hien = FirebaseDatabase.getInstance().getReference();
        hien.child("danhSachBanAn").child("ban"+ SetInforActivity.banSo).child("state")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int check = dataSnapshot.getValue(int.class);
                        if (check == 0) {
                            Intent mIntent = new Intent(InformationActivity.this,ThanksActivity.class);
                            startActivity(mIntent);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


        DatabaseReference temp = mData.child("danhSachBanAn").child("ban" + SetInforActivity.banSo).child("banSo").getRef();
        banSo.setText(SetInforActivity.banSo.toString());
        temp = mData.child("danhSachBanAn").child("ban" + SetInforActivity.banSo).child("khachHang").child("tenKhachHang").getRef();
        temp.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                tenKhachHang.setText(dataSnapshot.getValue().toString());
                dialog1.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                finish();
            }
        });


        lvThongTin.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                view.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });



        monAnArrayList = new ArrayList<>();
        temp = mData.child("danhSachBanAn").child("ban" + SetInforActivity.banSo).child("khachHang").child("danhSachMonAn");
        thongTinAdapter = new ThongTinAdapter(InformationActivity.this, R.layout.dong_thong_tin, monAnArrayList);
        lvThongTin.setAdapter(thongTinAdapter);
        temp.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                monAnArrayList.clear();
                dialog1.show();
                for (DataSnapshot ds : dataSnapshot.getChildren())
                {
                    MonAn monAn = ds.getValue(MonAn.class);
                    monAnArrayList.add(monAn);
                }
                thongTinAdapter.notifyDataSetChanged();
                dialog1.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            finish();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InformationActivity.this, SelectionActivity.class);
                startActivity(intent);
                onPause();
            }
        });

        btnThemMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InformationActivity.this, MenuActivity.class);
                startActivity(intent);
                onPause();
            }
        });

        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InformationActivity.this, PayActivity.class);
                startActivity(intent);
                onPause();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(InformationActivity.this, SelectionActivity.class);
        onPause();
        startActivity(intent);
    }
}
