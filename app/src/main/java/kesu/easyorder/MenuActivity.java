package kesu.easyorder;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
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
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * Created by thinhle on 9/16/17.
 */

public class MenuActivity extends AppCompatActivity {

    ArrayList<MonAn> list = new ArrayList<MonAn>();
    MonAnAdapter apater;
    private Button btnThemMon;
    private ImageButton btnBack;
    private TextView headline;
    public static TextView tv_tongtien;
    public static int tong_tien=0;
    private ListView lv;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ProgressDialog dialog1;
    private int max;


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //Hien code ----------------------


        DatabaseReference hien = FirebaseDatabase.getInstance().getReference();
        hien.child("danhSachBanAn").child("ban"+ SetInforActivity.banSo).child("state")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int check = dataSnapshot.getValue(int.class);
                        if (check == 0) {
                            Intent mIntent = new Intent(MenuActivity.this,Thanks.class);
                            startActivity(mIntent);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

        dialog1 = new ProgressDialog(this);
        dialog1.setMessage("Đang lấy dữ liệu");
        dialog1.setCancelable(false);
        dialog1.show();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("mon_an");
        apater = new MonAnAdapter(this, R.layout.mon, list);
        mDatabaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                MonAn temp;
                temp = dataSnapshot.getValue(MonAn.class);
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
        btnThemMon.setTypeface(f);
        dialog1.setMessage("Đang lấy dữ liệu");
        dialog1.setCancelable(false);
        mData.child("danhSachOrder").child("max").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                max = dataSnapshot.getValue(int.class);
                dialog1.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        btnThemMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isOnline()){
                    Toast.makeText(MenuActivity.this, "Vui lòng kết nối mạng!", Toast.LENGTH_SHORT).show();
                    return;
                }

                final ArrayList<MonAn> t_danhSachThemMon = new ArrayList<MonAn>();
                //them cac mon an da chon vao list can order them
                for (int i = 0; i < list.size(); i++)
                {
                    if (list.get(i).getSl() > 0) {
                        MonAn monAn = new MonAn(SetInforActivity.banSo, list.get(i).getGia(), list.get(i).getSl(), list.get(i).getState(), i, list.get(i).getTen(),list.get(i).getRate(), list.get(i).getId());
                        t_danhSachThemMon.add(monAn);
                    }
                }

                if (t_danhSachThemMon.size() == 0)
                {
                    Toast.makeText(MenuActivity.this, "Bạn chưa chọn món", Toast.LENGTH_SHORT).show();
                    dialog1.cancel();
                    return;
                }


                final AlertDialog.Builder builder = new AlertDialog.Builder(MenuActivity.this);
                builder.setTitle("Gọi món");
                builder.setMessage("Bạn có chắc chắn muốn gọi những món đã chọn không?");
                builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (MonAn ma : t_danhSachThemMon){
                            ma.setStt(max);
                            mData.child("danhSachOrder").child("danhSach").child(String.valueOf(max)).setValue(ma);
                            max ++;
                        }
                        mData.child("danhSachOrder").child("max").setValue(max);
                        dialog1.show();
                        for (MonAn ma : t_danhSachThemMon){
                            mData.child("danhSachBanAn").child("ban"+String.valueOf(ma.getBan())).child("khachHang")
                                    .child("danhSachMonAn").child(String.valueOf(ma.getStt())).setValue(ma);
                        }
                        dialog1.dismiss();
                        Toast.makeText(MenuActivity.this, "Đã thêm món vừa chọn!", Toast.LENGTH_SHORT).show();
                        onBackPressed();

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


