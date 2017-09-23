package kesu.easyorder;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by thinhle on 9/16/17.
 */

public class MenuActivity extends AppCompatActivity{

    ArrayList<ThongTinMonAn> list = new ArrayList<ThongTinMonAn>();
    MonAnAdapter apater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        list.add(new ThongTinMonAn("mon1","Lươn xào",20));
        list.add(new ThongTinMonAn("mon1","Lươn xào",30));
        list.add(new ThongTinMonAn("mon1","Lươn xào",60));
        list.add(new ThongTinMonAn("mon1","Lươn xào",90));
        list.add(new ThongTinMonAn("mon1","Lươn xào",70));

        apater = new MonAnAdapter(this, R.layout.mon,list);

        ListView lv = (ListView)findViewById(R.id.lv);
        lv.setAdapter(apater);
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

