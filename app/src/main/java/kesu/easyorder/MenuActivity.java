package kesu.easyorder;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * Created by thinhle on 9/16/17.
 */

public class MenuActivity extends AppCompatActivity {

    ArrayList<ThongTinMonAn> list = new ArrayList<ThongTinMonAn>();
    MonAnAdapter apater;
    private Button btnThemMon;
    private ImageButton btnBack;
    private TextView headline;
    public static TextView tv_tongtien;
    public static int tong_tien=0;
    private ListView lv;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //Hien code ----------------------
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("mon_an");
        apater = new MonAnAdapter(this, R.layout.mon, list);
        mDatabaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ThongTinMonAn temp;
                temp = dataSnapshot.getValue(ThongTinMonAn.class);
                list.add(temp);
                lv = (ListView) findViewById(R.id.lv);
                lv.setAdapter(apater);

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

        Typeface f = Typeface.createFromAsset(getAssets(), "fonts/UVNBanhMi.TTF");

        tv_tongtien = (TextView)findViewById(R.id.tv_tongtien_hien);
        tv_tongtien.setTypeface(f);

        final Handler handler = new Handler();
        final int delay = 1000;

        handler.postDelayed(new Runnable(){
            public void run(){
                tv_tongtien.setText("Tổng tiền: "+Integer.toString(MenuActivity.tong_tien)+"K");
                handler.postDelayed(this, delay);
            }
        }, delay);

        btnBack = (ImageButton) findViewById(R.id.btn_back);
        headline = (TextView) findViewById(R.id.tv_headline_chonmon) ;

        headline.setTypeface(f);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, SelectionActivity.class);
                startActivity(intent);
            }
        });

        // Thêm các món ăn đã chọn vao database.


        final DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
        btnThemMon = (Button) findViewById(R.id.btn_them_mon_menu);
        btnThemMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ArrayList<ThongTinMonAn> danhSachThemMon = new ArrayList<>();
                //them cac mon an da chon vao list can order them
                for (int i = 0; i < list.size(); i++)
                {
                    if (list.get(i).dang_chon > 0)
                    {
                        ThongTinMonAn thongTinMonAn = new ThongTinMonAn(list.get(i).id, list.get(i).name, list.get(i).gia, list.get(i).dang_chon);
                        danhSachThemMon.add(thongTinMonAn);
                        list.get(i).dang_chon = 0;
                    }
                }

                if (danhSachThemMon.size() == 0)
                {
                    //thong bao neu chua order mon
                    Toast.makeText(MenuActivity.this, "Bạn chưa chọn món!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    //tham chieu den danhSachMonAn hien tai de lay cac mon an da order truoc do
                    Query query = mData.child("danhSachBanAn").child("ban" + SetInforActivity.banSo).child("khachHang").child("danhSachMonAn").orderByKey().startAt("0");

                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot ds : dataSnapshot.getChildren())
                            {
                                ThongTinMonAn monAn = ds.getValue(ThongTinMonAn.class);
                                danhSachThemMon.add(monAn); // them vao danh sach them mon de ghi de du lieu
                            }

                            //them mon an
                            DatabaseReference temp = mData.child("danhSachBanAn").child("ban" + SetInforActivity.banSo).child("khachHang").child("danhSachMonAn");

                            temp.setValue(danhSachThemMon, new DatabaseReference.CompletionListener() {
                                @Override
                                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                    if (databaseError == null)
                                    {
                                        Toast.makeText(MenuActivity.this, "Đã thêm món vừa chọn!", Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    {
                                        Toast.makeText(MenuActivity.this, "Đã xảy ra lỗi!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }
        });

    }

}


