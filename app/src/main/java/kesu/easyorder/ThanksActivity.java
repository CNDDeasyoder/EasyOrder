package kesu.easyorder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ThanksActivity extends AppCompatActivity {
    TextView tvCamOn;
    Button btn;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanks);
        tvCamOn = (TextView) findViewById(R.id.tv_cam_on);
        btn = (Button)findViewById(R.id.btn_out);
        linearLayout = (LinearLayout) findViewById(R.id.linear_layout);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            finishAffinity();
            }
        });
        final DatabaseReference hien = FirebaseDatabase.getInstance().getReference();
        hien.child("danhSachBanAn").child("ban"+ SetInforActivity.banSo).child("state")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int check = dataSnapshot.getValue(int.class);
                        if (check == 0) {
                            tvCamOn.setVisibility(View.GONE);
                            linearLayout.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

    }

    @Override
    public void onBackPressed() {
        return;
    }
}
