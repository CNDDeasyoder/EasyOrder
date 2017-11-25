package kesu.easyorder;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
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
 * Created by thinhle on 11/23/17.
 */

public class ReviewActivity extends AppCompatActivity{
    ImageButton btnBack;
    Button btnXacNhan;
    ListView listView;
    TextView headline;
    TextView tvReview;
    ArrayList<MonAn> list;
    ReviewAdapter reviewAdapter;
    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
    ArrayList<String> arrayList; // chua id mon an
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        listView = (ListView) findViewById(R.id.lv_review);
        btnBack = (ImageButton) findViewById(R.id.btn_back_review);
        btnXacNhan = (Button) findViewById(R.id.btn_xac_nhan_review);
        headline = (TextView) findViewById(R.id.tv_headline_review);
        tvReview = (TextView) findViewById(R.id.tv_review);

        DatabaseReference hien = FirebaseDatabase.getInstance().getReference();
        hien.child("danhSachBanAn").child("ban"+ SetInforActivity.banSo).child("state")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int check = dataSnapshot.getValue(int.class);
                        if (check == 0) {
                            Intent mIntent = new Intent(ReviewActivity.this,ThanksActivity.class);
                            startActivity(mIntent);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


        Typeface f = Typeface.createFromAsset(getAssets(), "fonts/UVNBanhMi.TTF");
        headline.setTypeface(f);
        btnXacNhan.setTypeface(f);

        DatabaseReference hien1 = FirebaseDatabase.getInstance().getReference();
        hien1.child("danhSachBanAn").child("ban"+ SetInforActivity.banSo).child("state")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int check = dataSnapshot.getValue(int.class);
                        if (check == 0) {
                            Intent mIntent = new Intent(ReviewActivity.this,ThanksActivity.class);
                            startActivity(mIntent);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


        list = new ArrayList<>();
        arrayList = new ArrayList<>();

        DatabaseReference temp = mData.child("danhSachBanAn").child("ban" + SetInforActivity.banSo).child("khachHang");
        temp.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                for (DataSnapshot ds : dataSnapshot.getChildren())
                {
                    MonAn monAn = ds.getValue(MonAn.class);
                    if (!arrayList.contains(monAn.getId()))
                    {
                        arrayList.add(monAn.getId());
                        list.add(monAn);
                    }
                }
                reviewAdapter = new ReviewAdapter(ReviewActivity.this, R.layout.dong_review, list);
                listView.setAdapter(reviewAdapter);
                if (list.size() != 0)
                {
                    tvReview.setVisibility(View.GONE);
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

        // đưa dữ liệu lên firebase sau khi review
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(ReviewActivity.this);
                builder.setTitle("Review");
                builder.setCancelable(false);
                builder.setMessage("Xác nhận review");
                builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (MonAn temp: list)
                        {
                            String s = reviewAdapter.map.get(temp.getId());
                            Rate rate = temp.getRate();
                            int soNguoi = rate.getSoNguoi();
                            Rate rate1 = new Rate((soNguoi + 1), (rate.getSoSao() * soNguoi + Float.valueOf(s))/ (soNguoi + 1));
                            temp.setRate(new Rate((soNguoi + 1), (rate.getSoSao() * soNguoi + Float.valueOf(s))/ (soNguoi + 1)));
                            mData.child("mon_an").child(temp.getId()).child("rate").setValue(rate1);
                        }
                        Toast.makeText(ReviewActivity.this, "Cảm ơn quý khách!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ReviewActivity.this, SelectionActivity.class);
                        onPause();
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


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReviewActivity.this, SelectionActivity.class);
                onPause();
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        onPause();
        super.onBackPressed();
    }
}
