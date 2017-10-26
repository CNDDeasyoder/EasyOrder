package kesu.easyorder;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.solver.widgets.Snapshot;
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

import java.util.ArrayList;


/**
 * Created by thinhle on 9/16/17.
 */

public class MenuActivity extends AppCompatActivity {

    ArrayList<ThongTinMonAn> list = new ArrayList<ThongTinMonAn>();
    MonAnAdapter apater;
    private Button btnThemMon;
    private ImageButton btnBack;
    public static TextView tv_tongtien;
    public static int tong_tien=0;
    private ListView lv;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;


    private ArrayList<MonAn> danhSachMonAn;

    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();

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

        tv_tongtien = (TextView)findViewById(R.id.tv_tongtien_hien);

        final Handler handler = new Handler();
        final int delay = 1000;

        handler.postDelayed(new Runnable(){
            public void run(){
                tv_tongtien.setText("Tổng tiền: "+Integer.toString(MenuActivity.tong_tien)+"K");
                handler.postDelayed(this, delay);
            }
        }, delay);

        btnBack = (ImageButton) findViewById(R.id.btn_back);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, SelectionActivity.class);
                startActivity(intent);
            }
        });

        // Thêm các món ăn đã chọn vao database.

        final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

        btnThemMon = (Button) findViewById(R.id.btn_them_mon_menu);

        btnThemMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.child("KhachHang").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        KhachHang khachHang = dataSnapshot.getValue(KhachHang.class);

                        if (dataSnapshot.getKey().equals(SetInforActivity.keyUser))
                        {
                            Toast.makeText(MenuActivity.this, "key giong nhau", Toast.LENGTH_SHORT).show();
                            for (int i = 0; i < list.size(); i++)
                            {
                                if (list.get(i).dang_chon > 0)
                                {
                                    khachHang.getDanhSachMonAn().add(new MonAn(list.get(i).getTen_mon(),
                                            list.get(i).getDang_chon(), list.get(i).getGia()));
                                    list.get(i).dang_chon = 0;
                                }
                            }
                            mDatabase.child("KhachHang").child(SetInforActivity.keyUser).setValue(khachHang, new DatabaseReference.CompletionListener() {
                                @Override
                                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                    if (databaseError == null)
                                    {
                                        Toast.makeText(MenuActivity.this, "Đã thêm món!", Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    {
                                        Toast.makeText(MenuActivity.this, "Lỗi!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
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
            }
        });

    }

}


