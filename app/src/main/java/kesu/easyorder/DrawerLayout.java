package kesu.easyorder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;


import java.util.ArrayList;

/**
 * Created by thinhle on 9/22/17.
 */

public class DrawerLayout extends AppCompatActivity {

    private ListView listView;
    ArrayList<DrawerItem> list;
    DrawerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);

        listView = (ListView) findViewById(R.id.lv_drawer);
        list = new ArrayList<>();

        list.add(new DrawerItem("Trang chủ", R.drawable.ic_home_black_24dp));
        list.add(new DrawerItem("Menu", R.drawable.ic_restaurant_menu_black_24dp));
        list.add(new DrawerItem("Thông tin", R.drawable.ic_info_black_24dp));
        list.add(new DrawerItem("Thanh toán",R.drawable.ic_payment_black_24dp));

        adapter = new DrawerAdapter(this, R.layout.dong_drawer_layout, list);

        listView.setAdapter(adapter);

    }

}
