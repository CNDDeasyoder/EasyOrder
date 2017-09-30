package kesu.easyorder;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by thinhle on 9/16/17.
 */

public class MenuActivity extends AppCompatActivity {

    ArrayList<ThongTinMonAn> list = new ArrayList<ThongTinMonAn>();
    MonAnAdapter apater;
    public static TextView tv_tongtien;

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        list.add(new ThongTinMonAn("mon1", "Lươn xào", 20));
        list.add(new ThongTinMonAn("mon1", "Lươn xào", 30));
        list.add(new ThongTinMonAn("mon1", "Lươn xào", 60));
        list.add(new ThongTinMonAn("mon1", "Lươn xào", 90));
        list.add(new ThongTinMonAn("mon1", "Lươn xào", 90));
        list.add(new ThongTinMonAn("mon1", "Lươn xào", 90));
        list.add(new ThongTinMonAn("mon1", "Lươn xào", 90));
        list.add(new ThongTinMonAn("mon1", "Lươn xào", 70));

        apater = new MonAnAdapter(this, R.layout.mon, list);

        ListView lv = (ListView) findViewById(R.id.lv);
        lv.setAdapter(apater);

        tv_tongtien = (TextView)findViewById(R.id.tv_tongtien_hien);

        final Handler handler = new Handler();
        final int delay = 1000;

        handler.postDelayed(new Runnable(){
            public void run(){
                tv_tongtien.setText("Tổng tiền: "+Integer.toString(ThongTinMonAn.tong_tien)+"K");
                handler.postDelayed(this, delay);
            }
        }, delay);

       ImageButton btnBack = (ImageButton) findViewById(R.id.btn_back);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, SelectionActivity.class);
                startActivity(intent);
            }
        });


    }

}


