package kesu.easyorder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by thinhle on 11/23/17.
 */


public class ReviewAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<MonAn> monAnList;
    public Map<String, String> map = new HashMap<>();

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
        RatingBar ratingBar = (RatingBar) view.findViewById(R.id.ratingBar_review);
        final TextView number = (TextView) view.findViewById(R.id.tv_numb_review);


        final MonAn monAn = monAnList.get(i);
        map.put(monAn.getId(), "5.0");

        tvTen.setText(monAn.getTen());
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                number.setText(String.valueOf(rating));
                map.put(monAn.getId(), String.valueOf(rating));
            }
        });

        return view;
    }
}

