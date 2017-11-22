package kesu.easyorder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by thinhle on 11/23/17.
 */


public class ReviewAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<MonAn> monAnList;

    public ReviewAdapter(Context context, int layout, List<MonAn> monAnList) {
        this.context = context;
        this.layout = layout;
        this.monAnList = monAnList;
    }

    @Override
    public int getCount() {
        return monAnList.size();
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

        TextView tvTen = (TextView) view.findViewById(R.id.tv_ten_review);
        TextView tvSoLuong = (TextView) view.findViewById(R.id.tv_so_luong_review);
        TextView tvDonGia = (TextView) view.findViewById(R.id.tv_don_gia_review);

        MonAn monAn = monAnList.get(i);

        tvTen.setText(monAn.getTen());
        tvDonGia.setText(String.valueOf(monAn.getGia()));
        tvSoLuong.setText(String.valueOf(monAn.getSl()));

        return view;
    }
}

