package kesu.easyorder;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

public class MainActivity extends AppCompatActivity {

    private Button btnLogin;
    private EditText editText;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private String code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (!isOnline()){
            btnLogin = (Button) findViewById(R.id.btn_login);
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "Vui lòng kết nối mạng!", Toast.LENGTH_SHORT).show();
                }
            });
            return;
        }

        btnLogin = (Button) findViewById(R.id.btn_login);
        editText = (EditText)findViewById(R.id.edt_code);
        Typeface f = Typeface.createFromAsset(getAssets(), "fonts/UVNBanhMi.TTF");
        btnLogin.setTypeface(f);
        Query query = databaseReference.child("code").orderByValue();
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                code = dataSnapshot.getValue().toString();
                btnLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this, SetInforActivity.class);

                        if (editText.getText().toString().isEmpty())
                        {
                            Toast.makeText(MainActivity.this, "Vui lòng nhập code", Toast.LENGTH_SHORT).show();
                        }
                        else if ((editText.getText().toString().equals(code)))
                        {
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "        Code sai! \nCode gồm 4 chữ số.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

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
