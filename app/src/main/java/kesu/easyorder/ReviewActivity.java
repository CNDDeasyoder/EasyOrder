package kesu.easyorder;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

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

        Typeface f = Typeface.createFromAsset(getAssets(), "fonts/UVNBanhMi.TTF");
        headline.setTypeface(f);
        btnXacNhan.setTypeface(f);

        DatabaseReference hien = FirebaseDatabase.getInstance().getReference();
        hien.child("danhSachBanAn").child("ban"+ SetInforActivity.banSo).child("state")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int check = dataSnapshot.getValue(int.class);
                        if (check == 0) {
                            Intent mIntent = new Intent(ReviewActivity.this,Thanks.class);
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
                Intent intent = new Intent(ReviewActivity.this, SelectionActivity.class);
                startActivity(intent);
            }
        });
    }
}
