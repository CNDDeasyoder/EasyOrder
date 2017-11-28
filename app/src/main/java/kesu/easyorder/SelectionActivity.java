package kesu.easyorder;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
    private FloatingActionButton fl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        DatabaseReference hien1 = FirebaseDatabase.getInstance().getReference();
        hien1.child("danhSachBanAn").child("ban"+ SetInforActivity.banSo).child("state")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int check = dataSnapshot.getValue(int.class);
                        if (check == 0) {
                            Intent mIntent = new Intent(SelectionActivity.this,ThanksActivity.class);
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
                Intent intent = new Intent(SelectionActivity.this, ReviewActivity.class);
                startActivity(intent);
            }
        });
        fl = (FloatingActionButton)findViewById(R.id.fab);
        fl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(SelectionActivity.this);
                dialog.setTitle("Gọi nhân viên hỗ trợ")
                        .setMessage("Bạn có muốn gọi nhân viên hỗ trợ không")
                        .setNegativeButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                DatabaseReference q = FirebaseDatabase.getInstance().getReference();
                                q.child("danhSachBanAn").child("ban"+String.valueOf(SetInforActivity.banSo))
                                        .child("state").setValue(3);
                                Toast.makeText(SelectionActivity.this, "Đã gọi nhân viên hỗ trợ", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setCancelable(false)
                        .setNeutralButton("Không", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        return ;
    }
}
