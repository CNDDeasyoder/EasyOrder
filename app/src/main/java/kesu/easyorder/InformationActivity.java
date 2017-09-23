package kesu.easyorder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by thinhle on 9/16/17.
 */

public class InformationActivity extends AppCompatActivity{

    private ImageButton btnBack;
    ListView lvThongTin;
    ArrayList<MonAn> monAnArrayList;
    ThongTinAdapter thongTinAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor);

        btnBack = (ImageButton) findViewById(R.id.btn_nav_ttin_thinh);
        lvThongTin = (ListView)findViewById(R.id.lv_thong_tin);

        monAnArrayList = new ArrayList<>();
        monAnArrayList.add(new MonAn("Gà luộc", 3));
        monAnArrayList.add(new MonAn("Gà luộc", 2));
        monAnArrayList.add(new MonAn("Gà luộc", 2));
        monAnArrayList.add(new MonAn("Gà luộc", 2));
        monAnArrayList.add(new MonAn("Gà luộc", 2));
        monAnArrayList.add(new MonAn("Gà luộc", 2));
        monAnArrayList.add(new MonAn("Gà luộc", 2));
        monAnArrayList.add(new MonAn("Gà luộc", 2));
        monAnArrayList.add(new MonAn("Gà nướng", 2));
        thongTinAdapter = new ThongTinAdapter(this, R.layout.dong_thong_tin, monAnArrayList);
        lvThongTin.setAdapter(thongTinAdapter);


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InformationActivity.this, SelectionActivity.class);
                startActivity(intent);
            }
        });

    }

}
