package kesu.easyorder;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * Created by thinhle on 9/16/17.
 */

public class SelectionActivity extends AppCompatActivity {

    private Button btninfor;
    private Button btnPay;
    private Button btnMenu;
    private Button btnReview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        DatabaseReference hien = FirebaseDatabase.getInstance().getReference();
        hien.child("danhSachBanAn").child("ban"+ SetInforActivity.banSo).child("state")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int check = dataSnapshot.getValue(int.class);
                        if (check == 0) {
                            Intent mIntent = new Intent(SelectionActivity.this,Thanks.class);
                            startActivity(mIntent);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

        btninfor = (Button) findViewById(R.id.btn_infor);
        btnPay = (Button) findViewById(R.id.btn_pay);
        btnMenu = (Button) findViewById(R.id.btn_menu);
        btnReview = (Button) findViewById(R.id.btn_review);
        Typeface f = Typeface.createFromAsset(getAssets(), "fonts/UVNBanhMi.TTF");
        btninfor.setTypeface(f);
        btnMenu.setTypeface(f);
        btnPay.setTypeface(f);
        btnReview.setTypeface(f);


        btninfor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectionActivity.this, InformationActivity.class);
                startActivity(intent);
            }
        });

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectionActivity.this, PayActivity.class);
                startActivity(intent);
            }
        });

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectionActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
        btnReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SelectionActivity.this, "Coming SOON!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        return ;
    }
}
