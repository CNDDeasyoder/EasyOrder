package kesu.easyorder;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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
    private ProgressDialog dialog1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        dialog1 = new ProgressDialog(this);
        dialog1.setMessage("Đang lấy dữ liệu");
        dialog1.setCancelable(false);
        dialog1.show();

        DatabaseReference hien = FirebaseDatabase.getInstance().getReference();
        hien.child("danhSachBanAn").child("ban"+ SetInforActivity.banSo).child("state")
                .addValueEventListener(new ValueEventListener() {
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
        });////////////

        list = new ArrayList<>();



        DatabaseReference temp = mData.child("danhSachBanAn").child("ban" + SetInforActivity.banSo).child("khachHang");
        temp.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                for (DataSnapshot ds : dataSnapshot.getChildren())
                {
                    MonAn monAn = ds.getValue(MonAn.class);
                    list.add(monAn);

                }
                dialog1.dismiss();
                thanhToanAdapter = new ThanhToanAdapter(PayActivity.this, R.layout.dong_thanh_toan, list);
                lvThanhToan.setAdapter(thanhToanAdapter);


                // ham tinh tong tien can thanh toan
                int tong = 0;
                for (int i = 0; i < list.size(); i++)
                {
                    tong = tong + list.get(i).getGia() * list.get(i).getSl();
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
                if(!isOnline()){
                    Toast.makeText(PayActivity.this, "Vui lòng kết nối mạng!", Toast.LENGTH_SHORT).show();
                    return;
                }
                final AlertDialog.Builder builder = new AlertDialog.Builder(PayActivity.this);
                builder.setTitle("Yêu cầu thanh toán");
                builder.setCancelable(false);
                builder.setMessage("Bạn có chắc chắn muốn thanh toán?");
                builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog1.show();
                        mData.child("danhSachBanAn").child("ban" + SetInforActivity.banSo).child("state").setValue(2);
                        Intent intent = new Intent(PayActivity.this, Thanks.class);
                        dialog1.dismiss();
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("Huỷ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });
    }
    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}

