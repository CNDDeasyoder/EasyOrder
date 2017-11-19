package kesu.easyorder;

import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


/**
 * Created by thinhle on 9/16/17.
 */

public class SetInforActivity extends AppCompatActivity {
    private EditText edtName;
    GridView grv;
    ArrayList<BanAn> arrayList = new ArrayList<BanAn>();
    TableApater apater;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mReference;
    Button btn;
    public static Integer banSo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_infor);
        btn = (Button)findViewById(R.id.btn_sel);
        edtName = (EditText) findViewById(R.id.edt_set_name);
        grv = (GridView)findViewById(R.id.gr_table);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        Typeface f = Typeface.createFromAsset(getAssets(), "fonts/UVNBanhMi.TTF");
        btn.setTypeface(f);
        apater = new TableApater(this, R.layout.item_table,arrayList);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtName.getText().toString().isEmpty()) {
                    Toast.makeText(SetInforActivity.this, "Vui Lòng nhập tên", Toast.LENGTH_SHORT).show();
                    return;
                };
                final ProgressDialog dialog;
                dialog = new ProgressDialog(SetInforActivity.this);
                dialog.setCancelable(false);
                dialog.setMessage("Đang tải dữ liệu");
                final String name = edtName.getText().toString();
                grv.setAdapter(apater);
                mReference=mFirebaseDatabase.getReference().child("danhSachBanAn");
                mReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        arrayList.clear();
                        for(DataSnapshot data : dataSnapshot.getChildren()){

                            int boo = data.child("state").getValue(int.class);
                            int id = data.child("banSo").getValue(int.class);
                            BanAn temp = new BanAn(id,boo);
                            temp.setKhachHang(new KhachHang(name));
                            arrayList.add(temp);
                            Collections.sort(arrayList, new Comparator<BanAn>() {
                                @Override
                                public int compare(BanAn t1, BanAn t2) {
                                    if(t1.getBanSo()<t2.getBanSo()) return -1;
                                    else if (t1.getBanSo()==t2.getBanSo()) return 0;else
                                        return 1;
                                }
                            });
                            apater.notifyDataSetChanged();
                        }
                        dialog.dismiss();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });

    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
