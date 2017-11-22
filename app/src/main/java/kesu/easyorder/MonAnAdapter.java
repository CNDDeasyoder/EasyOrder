package kesu.easyorder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;


/**
 * Created by Tonqt Thonq on 9/22/2017.
 */

public class MonAnAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<MonAn> thongTinList;

    public MonAnAdapter(Context context, int layout, List<MonAn> thongTinList) {
        this.context = context;
        this.layout = layout;
        this.thongTinList = thongTinList;
    }

    @Override
    public int getCount() {
        return thongTinList.size();
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
    public View getView(int i, View view, final ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(layout, null);

        ImageView img = (ImageView)view.findViewById(R.id.hinh_anh);
        RatingBar ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);
        Button btn_add = (Button)view.findViewById(R.id.add);
        Button btn_sub = (Button)view.findViewById(R.id.sub);
        final EditText edt = (EditText) view.findViewById(R.id.sl);
        TextView ten_mon = (TextView)view.findViewById(R.id.ten_mon);
        final TextView gia = (TextView)view.findViewById(R.id.gia);
        final TextView tong =(TextView)view.findViewById(R.id.tong);



     //  img.setImageResource(R.drawable.mon1);

        final MonAn monan = thongTinList.get(i);

        StorageReference mStorageRef = FirebaseStorage.getInstance().getReference();
        StorageReference mStorageReference = mStorageRef.child("Image").child(monan.getId());
        Glide.with(context).using(new FirebaseImageLoader()).load(mStorageReference).into(img);
        
        edt.setText(Integer.toString(monan.getSl()));


        ten_mon.setText(monan.getTen());
        ratingBar.setRating(monan.getRate().getSoSao());
        gia.setText(monan.getGia()+"");
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edt.getText().toString().isEmpty() == true ) edt.setText(Integer.toString(0));
                int a = Integer.parseInt(edt.getText().toString());
                a++;
                monan.setSl(a);
                edt.setText(Integer.toString(a));
                tong.setText("Tổng: "+Integer.toString(monan.getGia()*a)+"K");
                MenuActivity.tong_tien+=monan.getGia();

                }
        });

        btn_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edt.getText().toString().isEmpty() == true ) edt.setText(Integer.toString(0));
                int a = Integer.parseInt(edt.getText().toString());
                if (a==0) return;
                a--;
                monan.setSl(a);
                edt.setText(Integer.toString(a));
                tong.setText("Tổng: "+Integer.toString(monan.getGia()*a)+"K");
                MenuActivity.tong_tien-=monan.getGia();

            }
        });

        return view;
    }
}
