package kesu.easyorder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Pattern;


/**
 * Created by thinhle on 9/16/17.
 */

public class SetInforActivity extends AppCompatActivity {
    private Button btnTable;
    private EditText edtName;
    private EditText edtTable;
    private DatabaseReference mData;
    public static Integer banSo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_infor);

        btnTable = (Button) findViewById(R.id.btn_submit_infor);
        edtName = (EditText) findViewById(R.id.edt_set_name);
        edtTable = (EditText) findViewById(R.id.edt_set_table);
        mData = FirebaseDatabase.getInstance().getReference();

        btnTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(SetInforActivity.this, SelectionActivity.class);
                String regExName = "^([a-zA-Z]+\\s)+[a-zA-Z]+\\s*$";

                String regExTable = "^[1-9]|1[0-1]$";

                Pattern pName = Pattern.compile(regExName);
                Pattern pTable = Pattern.compile(regExTable);
                final String name = edtName.getText().toString();
                final String table = edtTable.getText().toString();
                if (table.isEmpty() || name.isEmpty())
                {
                    Toast.makeText(SetInforActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                else if (pName.matches(regExName, name) && pTable.matches(regExTable, table))
                {
                    //neu ten va ban dung theo pattern thi kiem tra du lieu
                    Query query = mData.child("danhSachBanAn").orderByChild("banSo").equalTo(Integer.parseInt(table)); //loc du lieu theo so ban da nhap
                    query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot ds: dataSnapshot.getChildren())
                            {
                                BanAn banAn = ds.getValue(BanAn.class);
                                if (!banAn.getState()) //neu ban con trong thi them khach hang vao ban
                                {
                                    banSo = Integer.parseInt(table); //luu ban cua khach hang
                                    banAn.setKhachHang(new KhachHang(name));
                                    banAn.setState(true);
                                    mData.child("danhSachBanAn").child("ban" + String.valueOf(banAn.getBanSo())).setValue(banAn);
                                    Toast.makeText(SetInforActivity.this, "Đăng ký bàn thành công!", Toast.LENGTH_SHORT).show();
                                    startActivity(intent);
                                }
                                else
                                {
                                    Toast.makeText(SetInforActivity.this, "Bàn " + Integer.parseInt(table) + " chưa thể phục phụ quý khách!", Toast.LENGTH_SHORT).show();
                                }
                                //truong hop nguoc lai luon chay!!!!!!!!!!!!!!!
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
                else
                {
                    Toast.makeText(SetInforActivity.this, "Vui lòng nhập thông tin chính xác!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
