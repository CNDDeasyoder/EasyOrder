package kesu.easyorder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by thinhle on 9/22/17.
 */

public class DrawerAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<DrawerItem> list;


    public DrawerAdapter(Context context, int layout, List<DrawerItem> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override

    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(layout, null);

        TextView textView = (TextView) view.findViewById(R.id.tv_dong_drawer);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_dong_drawer);

        DrawerItem drawerItem = list.get(i);

        imageView.setImageResource(drawerItem.getImg());
        textView.setText(drawerItem.getTen());
        return view;
    }
}
